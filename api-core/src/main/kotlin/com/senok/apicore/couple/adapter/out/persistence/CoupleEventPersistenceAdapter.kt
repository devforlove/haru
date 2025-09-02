package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleEventMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleEventRepository
import com.senok.apicore.couple.application.out.SaveCoupleEventPort
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.stereotype.Component

@Component
class CoupleEventPersistenceAdapter(
    private val coupleEventRepository: CoupleEventRepository,
    private val mapper: CoupleEventMapper,
): SaveCoupleEventPort {

    override fun saveCoupleEvent(coupleEvent: CoupleEvent) {
        mapper.toEntity(coupleEvent)
            .let { entity -> coupleEventRepository.save(entity)}
    }
}