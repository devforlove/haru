package com.senok.config.auth

import com.senok.common.transaction.Tx
import com.senok.user.adapter.out.persistence.entity.RoleEntity
import com.senok.corecommon.type.user.RoleType
import com.senok.user.adapter.out.persistence.entity.UserEntity
import com.senok.user.adapter.out.persistence.repository.RoleRepository
import com.senok.user.adapter.out.persistence.repository.UserRepository
import com.senok.user.domain.auth.OAuth2UserInfo
import com.senok.user.domain.auth.PrincipalDetails
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        requireNotNull(userRequest) { "OAuth2UserRequest must not be null" }
        val userAttributes = super.loadUser(userRequest).attributes
        requireNotNull(userAttributes) { "UserAttribute must not be null" }

        val registrationId = userRequest.clientRegistration.registrationId
        val oAuth2UserInfo: OAuth2UserInfo = OAuth2UserInfo.of(registrationId, userAttributes)

        val user = Tx.run {
            getOrSaveUser(oAuth2UserInfo)
        }

        return PrincipalDetails(user, setOf(RoleType.ROLE_USER), userAttributes)
    }

    private fun getOrSaveUser(oAuth2UserInfo: OAuth2UserInfo): UserEntity {
        return userRepository.findByEmail(oAuth2UserInfo.email)
            ?: saveUser(oAuth2UserInfo)
    }

    private fun saveUser(oAuth2UserInfo: OAuth2UserInfo): UserEntity {
        val savedUser = userRepository.save(oAuth2UserInfo.toEntity())
        roleRepository.save(RoleEntity(savedUser.id, RoleType.ROLE_USER))
        return savedUser
    }
}