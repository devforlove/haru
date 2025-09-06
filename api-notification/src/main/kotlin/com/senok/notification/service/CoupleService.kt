package com.senok.notification.service

import com.senok.corecommon.types.couple.CoupleStatus
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import com.senok.notification.dao.entity.Couple
import com.senok.notification.dao.repository.CoupleRepository
import org.springframework.stereotype.Service

@Service
class CoupleService(
    private val coupleRepository: CoupleRepository,
) {
    
    fun saveCouple(event: CoupleEvent) {
        coupleRepository.saveCouple(
            Couple(
                event.attributes.femaleUserId,
                event.attributes.maleUserId,
                event.attributes.coupleCode!!,
                event.attributes.coupleCodeExpiredAt!!,
                CoupleStatus.INACTIVE
            )
        )
    }
    
    fun changeCouple(coupleId: Long, coupleStatus: CoupleStatus) {
        coupleRepository.updateCoupleStatus(coupleId, coupleStatus)
    }
}