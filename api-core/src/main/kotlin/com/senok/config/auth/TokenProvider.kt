package com.senok.config.auth

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
class TokenProvider {

    @Value("{jwt.key}")
    private lateinit var key: String
    private lateinit var secretKey: SecretKey

    companion object {
        private val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L
        private val KEY_ROLE = "role"
    }

    @PostConstruct
    private fun init() {
        secretKey = Keys.hmacShaKeyFor(key.toByteArray())
        println(secretKey)
    }

    fun generateAccessToken(authentication: Authentication): String {
        return generateToken(authentication)
    }

    fun getAuthentication(token: String): Authentication {
        val claims = parseClaims(token)
        val authorities = getAuthorities(claims)

        val principal = User(claims.subject, null, authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(token: String?): Boolean {
        if (!StringUtils.hasText(token)) {
            return false
        }

        val claims = parseClaims(token!!)
        return claims.expiration.after(Date())
    }

    private fun getAuthorities(claims: Claims): List<GrantedAuthority> {
        return claims[KEY_ROLE] as List<GrantedAuthority>
    }

    private fun generateToken(authentication: Authentication): String {
        val now = Date()
        val expireTime = Date(now.time + ACCESS_TOKEN_EXPIRE_TIME)

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