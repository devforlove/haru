package com.senok.user.application

import com.senok.integration.AbstractIntegrationSupport
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.application.out.UpdateUserPort
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import com.senok.apicore.fixtures.command.RegisterUserCommandFixture
import com.senok.apicore.fixtures.domain.UserFixture
import com.senok.user.adapter.out.persistence.entity.GenderType
import io.mockk.every

class RegisterUserServiceTest: AbstractIntegrationSupport({

    given("유저 등록시") {
        val findUserPort = mockk<FindUserPort>()
        val updateUserPort = mockk<UpdateUserPort>()
        val registerDevice = mockk<RegisterDevicePort>()
        val userId = 10L

        every { findUserPort.findUser(any()) } returns UserFixture.getUser(id = userId, nickname = "hihi", gender = GenderType.MALE)
        val sut = RegisterUserService(findUserPort, updateUserPort, registerDevice)

        `when`("정상적으로 유저가 등록되면") {
            val command = RegisterUserCommandFixture.getCommand()
            sut.registerUser(command, userId)

            then("user 테이블과, device 테이블에 유저 데이터가 생성된다.") {


            }
        }

        `when`("user 테이블에 유저 정보가 없으면") {
            val command = RegisterUserCommandFixture.getCommand()
            val result = sut.registerUser(command, userId)

            then("예외가 발생하고 더 이상 진행되지 않는다.") {

                result shouldBe 5
            }
        }
    }
})
