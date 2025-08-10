package com.senok.config.auth

import com.senok.coredb.transaction.Tx
import com.senok.user.adapter.out.persistence.entity.RoleType
import com.senok.user.adapter.out.persistence.entity.User
import com.senok.user.adapter.out.persistence.entity.UserRole
import com.senok.user.adapter.out.persistence.repository.UserRepository
import com.senok.user.domain.auth.OAuth2UserInfo
import com.senok.user.domain.auth.PrincipalDetails
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        requireNotNull(userRequest) { "OAuth2UserRequest must not be null" }
        val userAttributes = super.loadUser(userRequest).attributes
        requireNotNull(userAttributes) { "UserAttribute must not be null" }

        val registrationId = userRequest.clientRegistration.registrationId

        val oAuth2UserInfo: OAuth2UserInfo = OAuth2UserInfo.of(registrationId, userAttributes)

        val user = Tx.run {
             getOrSaveUser(oAuth2UserInfo)
            //TODO("role 추가")
        }

        return PrincipalDetails(user, setOf(RoleType.ROLE_USER), userAttributes)
    }

    private fun getOrSaveUser(oAuth2UserInfo: OAuth2UserInfo): User {
        return userRepository.findByEmail(oAuth2UserInfo.email)
            ?: userRepository.save(oAuth2UserInfo.toEntity())
    }
}