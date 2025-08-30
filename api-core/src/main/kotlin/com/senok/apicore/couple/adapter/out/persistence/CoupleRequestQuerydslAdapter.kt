package com.senok.apicore.couple.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import com.senok.apicore.couple.adapter.out.persistence.entity.QCoupleRequestEntity
import com.senok.apicore.couple.application.out.ChangeCoupleRequestPort
import com.senok.apicore.couple.domain.model.CoupleRequest
import org.springframework.stereotype.Component

@Component
class CoupleRequestQuerydslAdapter(
    private val queryFactory: JPAQueryFactory,
): ChangeCoupleRequestPort {

    override fun changeCoupleRequest(coupleRequest: CoupleRequest) {
        val qCoupleRequest = QCoupleRequestEntity.coupleRequestEntity
        queryFactory
            .update(qCoupleRequest)
            .set(qCoupleRequest.coupleRequestType, coupleRequest.coupleRequestType)
            .where(qCoupleRequest.coupleId.eq(coupleRequest.coupleId))
            .execute()
    }
}