package com.senok.apicore.user.domain.auth

import com.senok.apicore.user.domain.user.User

data class OAuth2UserInfo private constructor(
    val nickname: String,
    val email: String,
    val profile: String
) {

    fun toUser(): User {
        return User.initUser(
            email,
            nickname,
            profile
        )
    }

    companion object {

        fun of(registrationId: String, attributes: Map<String, Any>): OAuth2UserInfo {
            return when (registrationId) {
                "google" -> ofGoogle(attributes)
                "kakao" -> ofKakao(attributes)
                else -> throw IllegalArgumentException("Invalid OAuth2 user registration id: $registrationId")
            }
        }

        private fun ofGoogle(attributes: Map<String, Any>): OAuth2UserInfo {
            return OAuth2UserInfo(
                nickname = attributes["name"] as String,
                email = attributes["email"] as String,
                profile = attributes["picture"] as String,
            )
        }

        private fun ofKakao(attributes: Map<String, Any>): OAuth2UserInfo {
            val account = attributes["kakao_account"] as Map<*, *>
            val profile = account["profile"] as Map<*, *>

            return OAuth2UserInfo(
                nickname = profile["nickname"] as String,
                email = profile["email"] as? String ?: "inwook94@naver.com",
                profile = profile["profile_image_url"] as String
            )
        }
    }
}