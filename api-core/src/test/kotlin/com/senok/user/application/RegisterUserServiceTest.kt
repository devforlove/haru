package com.senok.user.application

import com.senok.integration.AbstractIntegrationSupport
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.application.out.UpdateUserPort
import com.senok.apicore.fixtures.command.RegisterUserCommandFixture
import com.senok.apicore.fixtures.domain.UserEntityFixture
import com.senok.user.adapter.out.persistence.entity.GenderType
import com.senok.user.adapter.out.persistence.repository.DeviceRepository
import com.senok.user.adapter.out.persistence.repository.UserRepository
import io.kotest.matchers.shouldBe

class RegisterUserServiceTest(
    private val findUserPort: FindUserPort,
    private val updateUserPort: UpdateUserPort,
    private val registerDevicePort: RegisterDevicePort,
    private val userRepository: UserRepository,
    private val deviceRepository: DeviceRepository,
): AbstractIntegrationSupport({
    val userId = 1L

    beforeTest {
        userRepository.save(UserEntityFixture.getUserEntity())
    }

    describe("유저 등록시") {
        context("정상적으로 유저가 등록되면") {
            it("user 테이블과, device 테이블에 유저 데이터가 생성된다.") {
                val command = RegisterUserCommandFixture.getCommand(nickname = "hihi", genderType = GenderType.MALE)

                val sut = RegisterUserService(findUserPort, updateUserPort, registerDevicePort)
                sut.registerUser(command, userId)

                val user = userRepository.findById(userId)
                val devices = deviceRepository.findByUserId(userId)

                user?.id shouldBe userId
                user?.gender shouldBe GenderType.MALE
                user?.nickname shouldBe "hihi"
                devices.size shouldBe 1
                devices[0].userId shouldBe userId
            }
        }
    }

    afterTest {
        userRepository.deleteById(userId)
    }
})
