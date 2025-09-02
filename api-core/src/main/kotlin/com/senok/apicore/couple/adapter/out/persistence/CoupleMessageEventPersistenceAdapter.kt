package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleMessageEventMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleMessageEventRepository
import com.senok.apicore.couple.application.out.SaveCoupleMessageEventPort
import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent
import org.springframework.stereotype.Component

@Component
class CoupleMessageEventPersistenceAdapter(
    private val repository: CoupleMessageEventRepository,
    private val mapper: CoupleMessageEventMapper
): SaveCoupleMessageEventPort {
    
    override fun saveCoupleMessageEvent(coupleMessageEvent: CoupleMessageEvent) {
        mapper.toEntity(coupleMessageEvent).apply {
            repository.save(this)
        }
    }
}