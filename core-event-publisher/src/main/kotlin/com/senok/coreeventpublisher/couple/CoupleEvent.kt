package com.senok.coreeventpublisher.couple

import com.senok.coreeventpublisher.common.DomainEvent
import java.time.LocalDateTime

class CoupleEvent(
    val coupleId: Long,
    override val eventType: CoupleEventType,
    override val attributes: CoupleEventAttribute,
    override val createdAt: LocalDateTime,
): DomainEvent<CoupleEventType, CoupleEvent.CoupleEventAttribute>(coupleId, eventType, attributes, createdAt) {

    data class CoupleEventAttribute(
        val coupleCodeExpiredAt: LocalDateTime? = null,
    )
}