package com.senok.apicore.couple.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import com.senok.apicore.common.transaction.Tx
import com.senok.apicore.couple.adapter.out.persistence.entity.QCoupleRequestEntity
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleRequestRepository
import com.senok.apicore.couple.application.out.ChangeCoupleRequestPort
import com.senok.apicore.couple.domain.model.CoupleRequest
import org.springframework.stereotype.Component

@Component
class CoupleRequestQuerydslAdapter(
    private val coupleRequestRepository: CoupleRequestRepository
): ChangeCoupleRequestPort {

    override fun changeCoupleRequest(coupleRequest: CoupleRequest) {
        Tx.writable {
            val coupleRequestEntity = coupleRequestRepository.findById(coupleRequest.coupleId)
            coupleRequestEntity.apply {
                this?.coupleRequestType = coupleRequest.coupleRequestType
            }
        }
    }
}