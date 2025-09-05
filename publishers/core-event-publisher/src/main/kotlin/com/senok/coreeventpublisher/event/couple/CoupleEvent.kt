package com.senok.coreeventpublisher.event.couple

import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class CoupleEvent(
    val coupleId: Long,
    val eventType: CoupleEventType,
    val attributes: CoupleEventAttribute,
    val createdAt: LocalDateTime,
): DomainEvent<CoupleEventType, CoupleEvent.CoupleEventAttribute>(coupleId, eventType, attributes, createdAt) {

    data class CoupleEventAttribute(
        val coupleCodeExpiredAt: LocalDateTime? = null,
    )
}