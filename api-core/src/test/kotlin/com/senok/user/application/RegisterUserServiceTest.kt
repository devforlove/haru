package com.senok.user.application

import com.senok.integration.AbstractIntegrationSupport
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.application.out.UpdateUserPort
import com.senok.apicore.fixtures.command.RegisterUserCommandFixture
import com.senok.apicore.fixtures.domain.UserEntityFixture
import com.senok.user.adapter.out.persistence.entity.DeviceEntity
import com.senok.corecommon.type.user.GenderType
import com.senok.user.adapter.out.persistence.entity.UserEntity
import com.senok.user.adapter.out.persistence.entity.UserEventEntity
import com.senok.user.adapter.out.persistence.repository.DeviceRepository
import com.senok.user.adapter.out.persistence.repository.UserEventRepository
import com.senok.user.adapter.out.persistence.repository.UserRepository
import io.kotest.matchers.shouldBe

class RegisterUserServiceTest(
    private val findUserPort: FindUserPort,
    private val updateUserPort: UpdateUserPort,
    private val registerDevicePort: RegisterDevicePort,
    private val userRepository: UserRepository,
    private val deviceRepository: DeviceRepository,
    private val userEventRepository: UserEventRepository,
): AbstractIntegrationSupport({
    val userId = 1L

    beforeTest {
        userRepository.save(UserEntityFixture.getUserEntity())
    }

    describe("유저 등록시") {
        context("정상적으로 유저가 등록되면") {
            it("user 테이블과, device 테이블에 유저 데이터가 생성되고, 이벤트가 발행된다.") {
                val command = RegisterUserCommandFixture.getCommand(nickname = "hihi", genderType = GenderType.MALE)

                val sut = RegisterUserService(findUserPort, updateUserPort, registerDevicePort)
                sut.registerUser(command, userId)

                val user = userRepository.findById(userId)
                val devices = deviceRepository.findByUserId(userId)
                val events = userEventRepository.findByUserId(userId)

                verifyUser(user, 1L, GenderType.MALE, "hihi")
                verifyDevice(devices, 1L)
                verifyEvent(events, 1L)
            }
        }
    }

    afterTest {
        // TODO("테이블 전체 제거 추가")
        userRepository.deleteById(userId)
    }
})

private fun verifyUser(user: UserEntity?, userId: Long, genderType: GenderType, nickname: String) {
    user?.id shouldBe userId
    user?.gender shouldBe genderType
    user?.nickname shouldBe nickname
}

private fun verifyDevice(devices: List<DeviceEntity>, userId: Long
) {
    devices.size shouldBe 1
    devices[0].userId shouldBe userId
}

private fun verifyEvent(events: List<UserEventEntity>, userId: Long) {
    events.size shouldBe 1
    events[0].userId shouldBe userId
    println(events[0].attributes)
}

