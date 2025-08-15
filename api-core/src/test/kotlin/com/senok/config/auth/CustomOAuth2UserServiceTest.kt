package com.senok.config.auth

import com.senok.integration.AbstractIntegrationSupport
import com.senok.user.adapter.out.persistence.repository.RoleRepository
import com.senok.user.adapter.out.persistence.repository.UserRepository
import io.mockk.mockk

class CustomOAuth2UserServiceTest : AbstractIntegrationSupport() {
    private final val userRepositoryMock = mockk<UserRepository>()
    private final val roleRepositoryMock = mockk<RoleRepository>()
    val sut = CustomOAuth2UserService(userRepositoryMock, roleRepositoryMock)

//    init {
//        given("loadUser") {
//            OAuth2UserRequest(null, null, null)
//            `when`("when") {
//
//            }
//        }
//    }
}
