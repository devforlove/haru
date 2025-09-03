package com.senok.apicore.user.application

import com.senok.apicore.common.integration.AbstractIntegrationSupport
import com.senok.apicore.fixtures.user.command.GetOrCreateUserCommandFixture
import com.senok.apicore.fixtures.user.command.RegisterUserCommandFixture
import com.senok.apicore.fixtures.user.entity.UserEntityFixture
import com.senok.apicore.user.adapter.out.persistence.repository.UserRepository
import com.senok.apicore.user.application.out.FindUserPort
import com.senok.apicore.user.application.out.SaveUserPort
import com.senok.corecommon.types.user.GenderType
import com.senok.coreeventpublisher.KafkaPublishVerifier
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.device.DeviceEvent
import com.senok.coreeventpublisher.event.device.DeviceEventType
import io.kotest.matchers.shouldBe

class GetOrCreateUserServiceTest(
    private val userRepository: UserRepository,
    private val findUserPort: FindUserPort,
    private val saveUserPort: SaveUserPort,
) : AbstractIntegrationSupport({
    
    beforeSpec {
        userRepository.save(UserEntityFixture.getUserEntity())
    }
    
    describe("유저 데이터 생성시") {
        context("정상적으로 유저가 등록되면") {
            it("유저 정보가 생성되고, 유저 이벤트가 발행된다.") {
                val command = GetOrCreateUserCommandFixture.getCommand()
                
                val sut = GetOrCreateUserService(findUserPort, saveUserPort)
                sut.getOrCreateUser(command)
                
            }
        }
    }
})
