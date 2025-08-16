package com.senok.user.application

import com.senok.integration.AbstractIntegrationSupport
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.application.out.UpdateUserPort
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class RegisterUserServiceTest : AbstractIntegrationSupport({

    given("register user") {
        val findUserPort = mockk<FindUserPort>()
        val updateUserPort = mockk<UpdateUserPort>()
        val registerDevice = mockk<RegisterDevicePort>()
        val sut = RegisterUserService(findUserPort, updateUserPort, registerDevice)

//        `when`("request registration") {
////            val command = RegisterUserCommandFixture.getCommand()
////            val result = sut.registerUser(command, 1L)
//
//            then("returns the sum") {
//                result shouldBe 5
//            }
//        }

    }
})
