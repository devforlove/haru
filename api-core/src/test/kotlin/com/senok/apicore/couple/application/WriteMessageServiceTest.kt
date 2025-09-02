package com.senok.apicore.couple.application

import com.senok.apicore.common.integration.AbstractIntegrationSupport
import com.senok.apicore.common.integration.IntegrationUtil.Companion.deleteAll
import com.senok.apicore.common.integration.IntegrationUtil.Companion.getQuery
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEventEntity
import com.senok.apicore.couple.adapter.out.persistence.entity.QCoupleMessageEntity
import com.senok.apicore.couple.adapter.out.persistence.entity.QCoupleMessageEventEntity
import com.senok.apicore.couple.adapter.out.persistence.entity.QIndividualEntity
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleMessageRepository
import com.senok.apicore.couple.adapter.out.persistence.repository.IndividualRepository
import com.senok.apicore.couple.application.out.FindCoupleMessagePort
import com.senok.apicore.couple.application.out.FindIndividualPort
import com.senok.apicore.couple.application.out.WriteCoupleMessagePort
import com.senok.apicore.fixtures.couple.command.WriteMessageCommandFixture
import com.senok.apicore.fixtures.couple.entity.CoupleMessageEntityFixture
import com.senok.apicore.fixtures.couple.entity.IndividualEntityFixture
import com.senok.corecommon.type.couple.CoupleMessageStatus
import com.senok.corecommon.type.couple.CoupleMessageType
import com.senok.corecommon.type.user.GenderType
import com.senok.coreeventpublisher.KafkaPublishVerifier
import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent
import com.senok.coreeventpublisher.event.couple.CoupleMessageEventType
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class WriteMessageServiceTest(
    private val individualRepository: IndividualRepository,
    private val coupleMessageRepository: CoupleMessageRepository,
    private val findCoupleMessagePort: FindCoupleMessagePort,
    private val findIndividualPort: FindIndividualPort,
    private val writeCoupleMessagePort: WriteCoupleMessagePort,
) : AbstractIntegrationSupport({
    val USER_ID = 1L
    val COUPLE_ID = 1L
    val COUPLE_MESSAGE_ID = 1L
    
    beforeSpec {
        coupleMessageRepository.save(CoupleMessageEntityFixture.getCoupleMessageEntity(COUPLE_ID, CoupleMessageType.NORMAL, CoupleMessageStatus.NEW))
        individualRepository.save(IndividualEntityFixture.getIndividualEntity(userId = USER_ID, gender = GenderType.MALE))
    }

    describe("유저가 커플 메세지 작성시") {
        context("정상적인 상황에서") {
            it("커플신청 관련 데이터가 데이터베이스에 생성되고, 상대방에게 이벤트가 발행된다.") {
                val command = WriteMessageCommandFixture.getCommand(COUPLE_ID, COUPLE_MESSAGE_ID, "I was ...", USER_ID)
                
                val sut = WriteMessageService(findCoupleMessagePort, findIndividualPort, writeCoupleMessagePort)
                sut.writeMessage(command)
                
                verifyCoupleMessage(COUPLE_MESSAGE_ID, "I was ...")
                val coupleMessageEventEntity = verifyCoupleMessageEvent(COUPLE_MESSAGE_ID)
                
                KafkaPublishVerifier.verify<CoupleMessageEvent>("couple.message.event") { event ->
                    event.coupleMessageId shouldBe COUPLE_MESSAGE_ID
                    event.eventType shouldBe CoupleMessageEventType.WRITE_MESSAGE
                    event.attributes.toString() shouldBe coupleMessageEventEntity.attributes
                }
            }
        }
    }

    afterSpec {
        deleteAll(QIndividualEntity.individualEntity)
        deleteAll(QCoupleMessageEntity.coupleMessageEntity)
    }
})

private fun verifyCoupleMessage(coupleMessageId: Long, text: String) {
    val coupleMessageEntity = getQuery()
        .selectFrom(QCoupleMessageEntity.coupleMessageEntity)
        .where(QCoupleMessageEntity.coupleMessageEntity.id.eq(coupleMessageId))
        .fetchOne()
    
    coupleMessageEntity shouldNotBe null
    coupleMessageEntity?.status shouldBe CoupleMessageStatus.COMPLETING
    coupleMessageEntity?.maleMessageContent?.text shouldBe text
}

private fun verifyCoupleMessageEvent(coupleMessageId: Long): CoupleMessageEventEntity {
    val coupleMessageEventEntityList = getQuery()
        .selectFrom(QCoupleMessageEventEntity.coupleMessageEventEntity)
        .where(QCoupleMessageEventEntity.coupleMessageEventEntity.coupleMessageId.eq(coupleMessageId))
        .fetch()
    
    coupleMessageEventEntityList.size shouldBe 1
    coupleMessageEventEntityList.first().eventType shouldBe CoupleMessageEventType.WRITE_MESSAGE
    return coupleMessageEventEntityList.first()
}