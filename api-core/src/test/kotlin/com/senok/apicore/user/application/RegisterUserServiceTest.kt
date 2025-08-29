package com.senok.apicore.user.application

import com.senok.apicore.fixtures.command.user.RegisterUserCommandFixture
import com.senok.apicore.fixtures.domain.user.UserEntityFixture
import com.senok.apicore.user.application.out.FindUserPort
import com.senok.apicore.user.application.out.RegisterDevicePort
import com.senok.apicore.user.application.out.UpdateUserPort
import com.senok.corecommon.type.user.GenderType
import com.senok.apicore.common.integration.AbstractIntegrationSupport
import com.senok.apicore.common.integration.IntegrationUtil
import com.senok.apicore.user.adapter.out.persistence.entity.QDeviceEntity
import com.senok.apicore.user.adapter.out.persistence.entity.QUserEntity
import com.senok.apicore.user.adapter.out.persistence.entity.QUserEventEntity
import com.senok.apicore.user.adapter.out.persistence.repository.UserRepository
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

                verifyUser(1L, GenderType.MALE, "hihi")
                verifyDevice( 1L)
                verifyEvent(1L)
            }
        }
    }

    afterSpec {
        IntegrationUtil.deleteAll(QUserEntity.userEntity)
        IntegrationUtil.deleteAll(QDeviceEntity.deviceEntity)
        IntegrationUtil.deleteAll(QUserEventEntity.userEventEntity)
    }
})

private fun verifyUser(userId: Long, genderType: GenderType, nickname: String) {
    val user = IntegrationUtil.getQuery()
        .selectFrom(QUserEntity.userEntity)
        .where(QUserEntity.userEntity.id.eq(userId))
        .fetchOne()
    user?.id shouldBe userId
    user?.gender shouldBe genderType
    user?.nickname shouldBe nickname
}

private fun verifyDevice(userId: Long
) {
    val devices = IntegrationUtil.getQuery()
        .selectFrom(QDeviceEntity.deviceEntity)
        .where(QDeviceEntity.deviceEntity.userId.eq(userId))
        .fetch()
    devices.size shouldBe 1
    devices[0].userId shouldBe userId
}

private fun verifyEvent(userId: Long) {
    val events = IntegrationUtil.getQuery()
        .selectFrom(QUserEventEntity.userEventEntity)
        .where(QUserEventEntity.userEventEntity.userId.eq(userId))
        .fetch()
    events.size shouldBe 1
    events[0].userId shouldBe userId
    println(events[0].attributes)
}

