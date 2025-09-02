package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleEventEntity
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.stereotype.Component

@Component
class CoupleEventMapper: CommonMapper<CoupleEventEntity, CoupleEvent> {

    override fun toEntity(domain: CoupleEvent): CoupleEventEntity {

        return CoupleEventEntity(
            coupleId = domain.coupleId,
            eventType = domain.eventType,
            attributes = domain.attributes.toString(),
        )
    }

    override fun toDomain(entity: CoupleEventEntity): CoupleEvent {
        throw UnsupportedOperationException("not supported function")
    }
}