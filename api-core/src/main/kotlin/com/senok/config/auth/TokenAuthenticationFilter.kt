package com.senok.config.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class TokenAuthenticationFilter(
    private val tokenProvider: TokenProvider
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = resolveToken(request)

        if (tokenProvider.validateToken(accessToken)) {
            setAuthentication(accessToken!!)
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (token.isNullOrEmpty()) {
            null
        } else {
            token
        }
    }

    private fun setAuthentication(accessToken: String) {
        val authentication = tokenProvider.getAuthentication(accessToken)
        SecurityContextHolder.getContext().authentication = authentication
    }
}