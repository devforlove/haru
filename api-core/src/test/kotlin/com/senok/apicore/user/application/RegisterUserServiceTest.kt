package com.senok.apicore.user.application

import com.senok.apicore.fixtures.command.user.RegisterUserCommandFixture
import com.senok.apicore.fixtures.domain.user.UserEntityFixture
import com.senok.apicore.user.application.out.FindUserPort
import com.senok.apicore.user.application.out.RegisterDevicePort
import com.senok.apicore.user.application.out.UpdateUserPort
import com.senok.corecommon.type.user.GenderType
import com.senok.apicore.common.integration.AbstractIntegrationSupport
import com.senok.apicore.common.integration.IntegrationUtil.Companion.deleteAll
import com.senok.apicore.common.integration.IntegrationUtil.Companion.getQuery
import com.senok.apicore.user.adapter.out.persistence.entity.*
import com.senok.apicore.user.adapter.out.persistence.repository.UserRepository
import com.senok.coreeventpublisher.KafkaPublishVerifier
import com.senok.coreeventpublisher.event.device.DeviceEvent
import com.senok.coreeventpublisher.event.device.DeviceEventType
import io.kotest.matchers.shouldBe

class RegisterUserServiceTest(
    private val findUserPort: FindUserPort,
    private val updateUserPort: UpdateUserPort,
    private val registerDevicePort: RegisterDevicePort,
    private val userRepository: UserRepository,
): AbstractIntegrationSupport({
    val userId = 1L

    beforeSpec {
        userRepository.save(UserEntityFixture.getUserEntity())
    }

    describe("유저 등록시") {
        context("정상적으로 유저가 등록되면") {
            it("user 테이블과, device 테이블에 유저 데이터가 생성되고, 이벤트가 발행된다.") {
                val command = RegisterUserCommandFixture.getCommand(nickname = "hihi", genderType = GenderType.MALE)

                val sut = RegisterUserService(findUserPort, updateUserPort, registerDevicePort)
                sut.registerUser(command, userId)

                verifyUserAndEvent(1L, GenderType.MALE, "hihi")
                val device = verifyDevice( 1L)
                val deviceEvent = verifyDeviceEvent(device.id!!)

                KafkaPublishVerifier.verify<DeviceEvent>("device.event") { event ->
                    event.deviceId shouldBe deviceEvent.deviceId
                    event.eventType shouldBe DeviceEventType.REGISTER
                    event.attributes.toString() shouldBe deviceEvent.attributes
                }
            }
        }
    }

    afterSpec {
        deleteAll(QUserEntity.userEntity)
        deleteAll(QDeviceEntity.deviceEntity)
        deleteAll(QUserEventEntity.userEventEntity)
    }
})

private fun verifyUserAndEvent(userId: Long, genderType: GenderType, nickname: String) {
    val user = getQuery()
        .selectFrom(QUserEntity.userEntity)
        .where(QUserEntity.userEntity.id.eq(userId))
        .fetchOne()
    val events = getQuery()
        .selectFrom(QUserEventEntity.userEventEntity)
        .where(QUserEventEntity.userEventEntity.userId.eq(userId))
        .fetch()

    user?.id shouldBe userId
    user?.gender shouldBe genderType
    user?.nickname shouldBe nickname
    events.size shouldBe 1
    events[0].userId shouldBe userId
}

private fun verifyDevice(userId: Long): DeviceEntity {
    val devices = getQuery()
        .selectFrom(QDeviceEntity.deviceEntity)
        .where(QDeviceEntity.deviceEntity.userId.eq(userId))
        .fetch()
    devices.size shouldBe 1
    devices[0].userId shouldBe userId
    return devices[0]
}

private fun verifyDeviceEvent(deviceId: Long): DeviceEventEntity {
    val events = getQuery()
        .selectFrom(QDeviceEventEntity.deviceEventEntity)
        .where(QDeviceEventEntity.deviceEventEntity.deviceId.eq(deviceId))
        .fetch()
    events.size shouldBe 1
    events[0].eventType shouldBe DeviceEventType.REGISTER
    return events[0]
}

