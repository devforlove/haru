package com.senok.apicore.couple.application

import com.senok.apicore.common.integration.AbstractIntegrationSupport
import com.senok.apicore.common.integration.IntegrationUtil
import com.senok.apicore.common.integration.IntegrationUtil.Companion.deleteAll
import com.senok.apicore.common.integration.IntegrationUtil.Companion.getQuery
import com.senok.apicore.couple.adapter.out.persistence.entity.QCoupleEntity
import com.senok.apicore.couple.adapter.out.persistence.entity.QCoupleRequestEntity
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleRepository
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleRequestRepository
import com.senok.apicore.couple.application.out.ChangeCouplePort
import com.senok.apicore.couple.application.out.ChangeCoupleRequestPort
import com.senok.apicore.couple.application.out.FindCouplePort
import com.senok.apicore.couple.application.out.FindCoupleRequestPort
import com.senok.apicore.fixtures.command.couple.AcceptCoupleCommandFixture
import com.senok.apicore.fixtures.domain.couple.CoupleEntityFixture
import com.senok.apicore.fixtures.domain.couple.CoupleRequestEntityFixture
import com.senok.corecommon.type.couple.CoupleRequestType
import com.senok.corecommon.type.couple.CoupleStatus
import io.kotest.matchers.shouldBe

class AcceptCoupleServiceTest(
    private val coupleRequestRepository: CoupleRequestRepository,
    private val coupleRepository: CoupleRepository,
    private val findCoupleRequestPort: FindCoupleRequestPort,
    private val findCouplePort: FindCouplePort,
    private val changeCoupleRequestPort: ChangeCoupleRequestPort,
    private val changeCouplePort: ChangeCouplePort,
) : AbstractIntegrationSupport({
    val userId = 1L
    val coupleId = 1L
    val coupleRequestId = 1L

    beforeSpec {
        coupleRequestRepository.save(CoupleRequestEntityFixture.getCoupleRequestEntity(coupleId = coupleId))
        coupleRepository.save(CoupleEntityFixture.getCoupleEntity(maleId = 2L, femaleId = userId, coupleStatus = CoupleStatus.REQUESTING))
    }

    describe("유저가 커플 요청 승인시") {
        context("요청을 승낙한다면") {
            it("커플 상태가 활성화 상태가 되고, 커플 요청 상태가 승낙 상태가 된다. 이후 커플 요청 이벤트가 발행된다.") {
                val command = AcceptCoupleCommandFixture.getCommand(userId, coupleRequestId, true)

                val sut = AcceptCoupleService(findCoupleRequestPort, findCouplePort, changeCoupleRequestPort, changeCouplePort)
                sut.acceptCouple(command)

                verifyCouple(coupleId)
                verifyCoupleRequest(coupleRequestId)
            }
        }
    }

    afterTest {
        deleteAll(QCoupleRequestEntity.coupleRequestEntity)
        deleteAll(QCoupleEntity.coupleEntity)
    }
})

private fun verifyCouple(coupleId: Long) {
    val couple = getQuery()
        .selectFrom(QCoupleEntity.coupleEntity)
        .where(QCoupleEntity.coupleEntity.id.eq(coupleId))
        .fetchOne()

    couple?.coupleStatus shouldBe CoupleStatus.ACTIVE
}

private fun verifyCoupleRequest(coupleRequestId: Long) {
    val coupleRequest = getQuery()
        .selectFrom(QCoupleRequestEntity.coupleRequestEntity)
        .where(QCoupleRequestEntity.coupleRequestEntity.coupleId.eq(coupleRequestId))
        .fetchOne()

    coupleRequest?.coupleRequestType shouldBe CoupleRequestType.ACCEPTED
}