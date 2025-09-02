package com.senok.apicore.couple.domain.model

import com.senok.apicore.common.domain.DomainModel
import com.senok.corecommon.types.couple.CoupleRequestType
import com.senok.coreeventpublisher.event.couple.CoupleRequestEvent
import com.senok.coreeventpublisher.event.couple.CoupleRequestEventType
import java.time.LocalDateTime

class CoupleRequest(
    val coupleId: Long,
    var coupleRequestType: CoupleRequestType,
): DomainModel<CoupleRequest>() {
    
    companion object {
        fun initRequest(coupleId: Long): CoupleRequest {
            return CoupleRequest(
                coupleId = coupleId,
                coupleRequestType = CoupleRequestType.REQUESTING
            )
        }
    }

    val isRequesting = coupleRequestType == CoupleRequestType.REQUESTING

    fun changeTypeOnRequest(isAccepted: Boolean) {
        coupleRequestType = if (isAccepted) {
            CoupleRequestType.ACCEPTED
        } else {
            CoupleRequestType.REJECTED
        }
        
        publishCoupleRequestEvent(coupleRequestType)
    }
    
    private fun publishCoupleRequestEvent(coupleRequestType: CoupleRequestType) {
        publishEvent(
            CoupleRequestEvent(
                coupleRequestId = id,
                eventType = if(coupleRequestType == CoupleRequestType.ACCEPTED)
                    CoupleRequestEventType.ACCEPTED else CoupleRequestEventType.REJECTED,
                attributes = Unit,
                createdAt = LocalDateTime.now()
            )
        )
    }
}