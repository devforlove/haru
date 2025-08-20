package com.senok.user.application

import com.senok.apicore.fixtures.command.RegisterUserCommandFixture
import com.senok.apicore.fixtures.domain.UserFixture
import com.senok.user.adapter.out.persistence.entity.GenderType
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.dto.RegisterInfoDto
import com.senok.user.domain.user.Device
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify

class RegisterUserServiceTest: DescribeSpec({
    val userId = 1L
    val findUserPort = mockk<FindUserPort>()
    val updateUserPort = mockk<UpdateUserPort>()
    val registerDevicePort = mockk<RegisterDevicePort>()

    describe("유저 등록시") {
        context("정상적으로 유저가 등록되면") {
            it("user 테이블과, device 테이블에 유저 데이터가 생성된다.") {
                val command = RegisterUserCommandFixture.getCommand(nickname = "hihi", genderType = GenderType.MALE)
                every { findUserPort.findUser(userId) } returns UserFixture.getUser(userId)
                every { updateUserPort.updateRegisterInfo(any()) } returns Unit
                every { registerDevicePort.registerDevice(any()) } returns Unit

                val sut = RegisterUserService(findUserPort, updateUserPort, registerDevicePort)
                sut.registerUser(command, userId)

                val userInfo = slot<RegisterInfoDto>()
                val device = slot<Device>()
                verify(exactly = 1) { findUserPort.findUser(userId) }
                verify(exactly = 1) { updateUserPort.updateRegisterInfo(capture(userInfo)) }
                verify(exactly = 1) { registerDevicePort.registerDevice(capture(device)) }

                userInfo.captured.userId shouldBe userId
                userInfo.captured.genderType shouldBe GenderType.MALE
                userInfo.captured.nickname shouldBe "hihi"
                device.captured.ownerId shouldBe userId
            }
        }
    }
})
