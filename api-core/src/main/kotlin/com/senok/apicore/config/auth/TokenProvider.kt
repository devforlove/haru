package com.senok.apicore.config.auth

import com.senok.apicore.user.domain.auth.PrincipalDetails
import com.senok.corecommon.types.user.RoleType
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
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
        private val USER_EMAIL = "user_email"
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
        val userEmail = claims[USER_EMAIL] as String
        val userId = (claims[USER_ID] as Number).toLong()

        val principal = PrincipalDetails(userEmail, userId, authorities.toSet())
        return UsernamePasswordAuthenticationToken(
            principal,
            token,
            authorities.map { roleType -> GrantedAuthority { roleType.toString() } }
        )
    }

    fun validateToken(token: String?): Boolean {
        if (!StringUtils.hasText(token)) {
            return false
        }

        val claims = parseClaims(token!!)
        return claims.expiration.after(Date())
    }

    private fun getAuthorities(claims: Claims): List<RoleType> {
        return (claims[KEY_ROLE] as List<*>)
            .map { RoleType.valueOf(it as String) }
    }

    private fun generateToken(authentication: Authentication): String {
        val now = Date()
        val expireTime = Date(now.time + expireTime.toLong())

        val principalDetails = authentication.principal as PrincipalDetails
        val authorities = authentication.authorities
            .map { authority -> authority.authority }
            .toList()

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(KEY_ROLE, authorities)
            .claim(USER_ID, principalDetails.id)
            .claim(USER_EMAIL, principalDetails.username)
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