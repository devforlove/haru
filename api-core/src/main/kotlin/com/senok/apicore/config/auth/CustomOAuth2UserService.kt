package com.senok.apicore.config.auth

import com.senok.apicore.user.adapter.out.persistence.entity.UserEntity
import com.senok.apicore.user.domain.auth.OAuth2UserInfo
import com.senok.apicore.user.domain.auth.PrincipalDetails
import com.senok.corecommon.types.user.RoleType
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userRepository: com.senok.apicore.user.adapter.out.persistence.repository.UserRepository,
    private val roleRepository: com.senok.apicore.user.adapter.out.persistence.repository.RoleRepository
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        requireNotNull(userRequest) { "OAuth2UserRequest must not be null" }
        val userAttributes = super.loadUser(userRequest).attributes
        requireNotNull(userAttributes) { "UserAttribute must not be null" }

        val registrationId = userRequest.clientRegistration.registrationId
        val oAuth2UserInfo: OAuth2UserInfo = OAuth2UserInfo.of(registrationId, userAttributes)

        val user = getOrSaveUser(oAuth2UserInfo)

        return PrincipalDetails(user, setOf(RoleType.ROLE_USER), userAttributes)
    }

    private fun getOrSaveUser(oAuth2UserInfo: OAuth2UserInfo): UserEntity {
        return userRepository.findByEmail(oAuth2UserInfo.email)
            ?: saveUser(oAuth2UserInfo)
    }

    private fun saveUser(oAuth2UserInfo: OAuth2UserInfo): UserEntity {
        val savedUser = userRepository.save(oAuth2UserInfo.toEntity())
        roleRepository.save(
            com.senok.apicore.user.adapter.out.persistence.entity.RoleEntity(
                savedUser.id,
                RoleType.ROLE_USER
            )
        )
        return savedUser
    }
}