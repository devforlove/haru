package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleCodeEventMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleEventRepository
import com.senok.apicore.couple.application.out.SaveCoupleCodeEventPort
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.stereotype.Component

@Component
class CoupleEventPersistenceAdapter(
    private val coupleEventRepository: CoupleEventRepository,
    private val mapper: CoupleCodeEventMapper,
): SaveCoupleCodeEventPort {

    override fun saveCoupleCodeEvent(coupleEvent: CoupleEvent) {
        mapper.toEntity(coupleEvent)
            .let { entity -> coupleEventRepository.save(entity)}
    }
}