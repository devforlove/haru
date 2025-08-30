package com.senok.apicore.couple.application

import com.senok.apicore.couple.adapter.out.persistence.entity.*
import com.senok.apicore.couple.adapter.out.persistence.repository.IndividualRepository
import com.senok.apicore.couple.domain.service.ValidateRequestService
import com.senok.apicore.fixtures.command.couple.RequestCoupleCommandFixture
import com.senok.apicore.fixtures.domain.couple.IndividualEntityFixture
import com.senok.apicore.common.integration.AbstractIntegrationSupport
import com.senok.apicore.common.integration.IntegrationUtil
import com.senok.apicore.common.integration.IntegrationUtil.Companion.deleteAll
import com.senok.apicore.common.integration.IntegrationUtil.Companion.getQuery
import com.senok.apicore.couple.application.out.*
import com.senok.corecommon.type.couple.CoupleRequestType
import com.senok.corecommon.type.user.GenderType
import com.senok.coreeventpublisher.KafkaPublishVerifier
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import com.senok.coreeventpublisher.event.couple.CoupleEventType
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class RequestCoupleServiceTest(
    private val individualRepository: IndividualRepository,
    private val findIndividualPort: FindIndividualPort,
    private val validateRequestService: ValidateRequestService,
    private val findCouplePort: FindCouplePort,
    private val saveCouplePort: SaveCouplePort,
    private val saveCoupleRequestPort: SaveCoupleRequestPort,
    private val saveCoupleCodePort: SaveCoupleCodePort,
) : AbstractIntegrationSupport({
    val maleId = 1L
    val femaleId = 2L

    beforeSpec {
        individualRepository.save(IndividualEntityFixture.getIndividualEntity(userId = maleId, gender = GenderType.MALE))
        individualRepository.save(IndividualEntityFixture.getIndividualEntity(userId = femaleId, gender = GenderType.FEMALE))
    }

    describe("유저가 특정 유저에게 커플 신청시") {
        context("정상적인 상황에서") {
            it("커플신청 관련 데이터가 데이터베이스에 생성되고, 상대방에게 이벤트가 발행된다.") {
                val command = RequestCoupleCommandFixture.getCommand(requestedUserId = femaleId, userId = maleId)

                val sut = RequestCoupleService(findIndividualPort, validateRequestService, findCouplePort, saveCouplePort, saveCoupleRequestPort, saveCoupleCodePort)
                sut.requestCouple(command)

                val coupleEntity = verifyCouple(femaleId, maleId)
                val coupleEventEntity = verifyCoupleEvent(coupleEntity.id!!)
                verifyCoupleCode(coupleEntity.id!!)
                verifyCoupleRequest(coupleEntity.id!!, CoupleRequestType.REQUESTING)

                KafkaPublishVerifier.verify<CoupleEvent>("couple.event") { event ->
                    event.coupleId shouldBe coupleEventEntity.coupleId
                    event.eventType shouldBe CoupleEventType.REQUESTING
                    event.attributes.toString() shouldBe coupleEventEntity.attributes
                }
            }
        }
    }

    afterSpec {
        deleteAll(QIndividualEntity.individualEntity)
        deleteAll(QCoupleCodeEntity.coupleCodeEntity)
        deleteAll(QCoupleEventEntity.coupleEventEntity)
    }
})

private fun verifyCouple(femaleId: Long, maleId: Long): CoupleEntity {
    val couple = getQuery()
        .selectFrom(QCoupleEntity.coupleEntity)
        .where(
            QCoupleEntity.coupleEntity.femaleId.eq(femaleId)
                .and(QCoupleEntity.coupleEntity.maleId.eq(maleId))
        )
        .fetchOne()

    couple shouldNotBe null
    return couple!!
}

private fun verifyCoupleEvent(coupleId: Long): CoupleEventEntity {
    val events = getQuery().selectFrom(QCoupleEventEntity.coupleEventEntity)
        .where(
            QCoupleEventEntity.coupleEventEntity.coupleId.eq(coupleId)
        )
        .fetch()
    events.size shouldBe 1
    events[0].eventType shouldBe CoupleEventType.REQUESTING
    return events[0]
}

private fun verifyCoupleRequest(coupleId: Long, coupleRequestType: CoupleRequestType) {
    val coupleRequest = getQuery().selectFrom(QCoupleRequestEntity.coupleRequestEntity)
        .where(
            QCoupleRequestEntity.coupleRequestEntity.coupleId.eq(coupleId)
        )
        .fetchOne()
    coupleRequest shouldNotBe null
    coupleRequest?.coupleId shouldBe coupleId
    coupleRequest?.coupleRequestType shouldBe coupleRequestType
}

private fun verifyCoupleCode(coupleId: Long): CoupleCodeEntity {
    val coupleCode = getQuery()
        .selectFrom(QCoupleCodeEntity.coupleCodeEntity)
        .where(
            QCoupleCodeEntity.coupleCodeEntity.coupleId.eq(coupleId)
        )
        .fetchOne()

    coupleCode shouldNotBe null
    return coupleCode!!
}
