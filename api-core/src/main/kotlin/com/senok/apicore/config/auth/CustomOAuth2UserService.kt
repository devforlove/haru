package com.senok.apicore.config.auth

import com.senok.apicore.user.application.GetOrCreateUserService
import com.senok.apicore.user.application.`in`.GetOrCreateUserUseCase
import com.senok.apicore.user.application.`in`.command.GetOrCreateUserCommand
import com.senok.apicore.user.domain.auth.OAuth2UserInfo
import com.senok.apicore.user.domain.auth.PrincipalDetails
import com.senok.corecommon.types.user.RoleType
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val getOrCreateUserService: GetOrCreateUserService
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        requireNotNull(userRequest) { "OAuth2UserRequest must not be null" }
        val userAttributes = super.loadUser(userRequest).attributes
        requireNotNull(userAttributes) { "UserAttribute must not be null" }

        val registrationId = userRequest.clientRegistration.registrationId
        val userInfo: OAuth2UserInfo = OAuth2UserInfo.of(registrationId, userAttributes)
        
        val user = getOrCreateUserService.getOrCreateUser(
            GetOrCreateUserCommand(userInfo.email, userInfo.nickname, userInfo.profile)
        )
        return PrincipalDetails(user, setOf(RoleType.ROLE_USER), userAttributes)
    }
}