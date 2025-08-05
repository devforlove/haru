package com.senok.user.config.auth

import com.senok.user.adapter.out.persistence.entity.User
import com.senok.user.adapter.out.persistence.repository.UserRepository
import com.senok.user.domain.auth.OAuth2UserInfo
import com.senok.user.domain.auth.PrincipalDetails
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomOAuth2UserService(
    val userRepository: UserRepository,
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        requireNotNull(userRequest) { "OAuth2UserRequest must not be null" }
        val userAttributes = super.loadUser(userRequest).attributes
        requireNotNull(userAttributes) { "UserAttribute must not be null" }

        val registrationId = userRequest.clientRegistration.registrationId
        val userNameAttributeName = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val oAuth2UserInfo: OAuth2UserInfo = OAuth2UserInfo.of(registrationId, userAttributes)
        val user = getOrSaveUser(oAuth2UserInfo)

        return PrincipalDetails(user, setOf(), userAttributes, userNameAttributeName)
    }

    private fun getOrSaveUser(oAuth2UserInfo: OAuth2UserInfo): User {
        return userRepository.findByEmail(oAuth2UserInfo.email)
            ?: userRepository.save(oAuth2UserInfo.toEntity())
    }
}