package com.senok.config.auth

import com.senok.user.adapter.out.persistence.entity.RoleType
import com.senok.user.domain.auth.PrincipalDetails
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*
import javax.crypto.SecretKey

@Component
class TokenProvider(
    @Value("\${jwt.secret}")
    private val key: String,
    @Value("\${jwt.expiration_time}")
    private val expireTime: String
) {

    private lateinit var secretKey: SecretKey

    companion object {
        private val KEY_ROLE = "role"
        private val USER_ID = "user_id"
    }

    @PostConstruct
    private fun init() {
        secretKey = Keys.hmacShaKeyFor(key.toByteArray())
    }

    fun generateAccessToken(authentication: Authentication): String {
        return generateToken(authentication)
    }

    fun getAuthentication(token: String): Authentication {
        val claims = parseClaims(token)
        val authorities = getAuthorities(claims)

        val principal = PrincipalDetails(claims.subject, claims[USER_ID] as Long, authorities.toSet())
        return UsernamePasswordAuthenticationToken(principal, token, authorities.map { GrantedAuthority { it.toString() } })
    }

    fun validateToken(token: String?): Boolean {
        if (!StringUtils.hasText(token)) {
            return false
        }

        val claims = parseClaims(token!!)
        return claims.expiration.after(Date())
    }

    private fun getAuthorities(claims: Claims): List<RoleType> {
        return claims[KEY_ROLE] as List<RoleType>
    }

    private fun generateToken(authentication: Authentication): String {
        val now = Date()
        val expireTime = Date(now.time + expireTime.toLong())

        val authorities = authentication.authorities
            .map { authority -> authority.authority }
            .toList()

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(KEY_ROLE, authorities)
            .setIssuedAt(now)
            .setExpiration(expireTime)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact()
    }

    private fun parseClaims(token: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).body
        } catch (e : ExpiredJwtException) {
            return e.claims
        } catch (e : MalformedJwtException) {
            // TODO(토큰 예외 정리)
            throw Exception("JWT token expired", e)
        } catch (e : SecurityException) {
            // TODO(토큰 예외 정리)
            throw Exception("JWT token expired", e)
        } catch (e: Exception) {
            throw Exception("unknown exception", e)
        }
    }
}