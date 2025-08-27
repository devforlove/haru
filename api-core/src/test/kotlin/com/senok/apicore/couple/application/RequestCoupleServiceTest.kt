package com.senok.apicore.couple.application

import com.querydsl.jpa.impl.JPAQueryFactory
import com.senok.apicore.couple.adapter.out.persistence.entity.*
import com.senok.apicore.couple.adapter.out.persistence.repository.IndividualRepository
import com.senok.apicore.couple.application.out.FindCouplePort
import com.senok.apicore.couple.application.out.FindIndividualPort
import com.senok.apicore.couple.application.out.SaveCoupleCodePort
import com.senok.apicore.couple.application.out.SaveCouplePort
import com.senok.apicore.couple.domain.service.ValidateRequestService
import com.senok.apicore.fixtures.command.couple.RequestCoupleCommandFixture
import com.senok.apicore.fixtures.domain.couple.IndividualEntityFixture
import com.senok.apicore.integration.AbstractIntegrationSupport
import com.senok.apicore.integration.IntegrationUtilService
import com.senok.corecommon.type.user.GenderType
import io.kotest.matchers.shouldNotBe

class RequestCoupleServiceTest(
    private val individualRepository: IndividualRepository,
    private val findIndividualPort: FindIndividualPort,
    private val validateRequestService: ValidateRequestService,
    private val findCouplePort: FindCouplePort,
    private val saveCouplePort: SaveCouplePort,
    private val saveCoupleCodePort: SaveCoupleCodePort,
    private val integrationUtilService: IntegrationUtilService
) : AbstractIntegrationSupport({
    val maleId = 1L
    val femaleId = 2L

    beforeSpec {
        individualRepository.save(IndividualEntityFixture.getUserEntity(userId = maleId, gender = GenderType.MALE))
        individualRepository.save(IndividualEntityFixture.getUserEntity(userId = femaleId, gender = GenderType.FEMALE))
    }

    describe("유저가 특정 유저에게 커플 신청시") {
        context("정상적인 상황에서") {
            it("커플신청 관련 데이터가 데이터베이스에 생성되고, 상대방에게 이벤트가 발행된다.") {
                val command = RequestCoupleCommandFixture.getCommand(requestedUserId = femaleId, userId = maleId)

                val sut = RequestCoupleService(findIndividualPort, validateRequestService, findCouplePort, saveCouplePort, saveCoupleCodePort)
                sut.requestCouple(command)

                val couple = verifyCouple(integrationUtilService.getQuery(), femaleId, maleId)
                verifyCoupleCode(integrationUtilService.getQuery(), couple.id!!)
            }
        }
    }

    afterSpec {
        integrationUtilService.deleteAll(QIndividualEntity.individualEntity)
    }
})

private fun verifyCouple(
    query: JPAQueryFactory,
    femaleId: Long,
    maleId: Long
): CoupleEntity {
    val couple = query
        .selectFrom(QCoupleEntity.coupleEntity)
        .where(
            QCoupleEntity.coupleEntity.femaleId.eq(femaleId)
                .and(QCoupleEntity.coupleEntity.maleId.eq(maleId))
        )
        .fetchOne()

    couple shouldNotBe null
    return couple!!
}

private fun verifyCoupleCode(
    query: JPAQueryFactory,
    coupleId: Long): CoupleCodeEntity {

    val coupleCode = query
        .selectFrom(QCoupleCodeEntity.coupleCodeEntity)
        .where(
            QCoupleCodeEntity.coupleCodeEntity.coupleId.eq(coupleId)
        )
        .fetchOne()

    coupleCode shouldNotBe null
    return coupleCode!!
}
