package com.senok.config.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2SuccessHandler(
    private val tokenProvider: TokenProvider
): AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        requireNotNull(authentication) { "Authentication can not be null on successHandler" }
        val accessToken = tokenProvider.generateAccessToken(authentication)

        response?.setHeader("Access-Token", accessToken)
    }
}