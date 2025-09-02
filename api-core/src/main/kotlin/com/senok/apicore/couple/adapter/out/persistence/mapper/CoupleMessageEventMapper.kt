package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleCodeEntity
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEventEntity
import com.senok.apicore.couple.domain.model.CoupleCode
import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent
import org.springframework.stereotype.Component

@Component
class CoupleMessageEventMapper: CommonMapper<CoupleMessageEventEntity, CoupleMessageEvent> {
    
    override fun toEntity(domain: CoupleMessageEvent): CoupleMessageEventEntity {
        return CoupleMessageEventEntity(
            coupleMessageId = domain.coupleMessageId,
            eventType = domain.eventType,
            attributes = domain.attributes.toString(),
        )
    }
    
    override fun toDomain(entity: CoupleMessageEventEntity): CoupleMessageEvent {
        throw UnsupportedOperationException("not supported function")
    }
}