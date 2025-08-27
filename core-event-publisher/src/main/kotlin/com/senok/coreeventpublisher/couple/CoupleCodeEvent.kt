package com.senok.coreeventpublisher.couple

import com.senok.coreeventpublisher.common.DomainEvent
import java.time.LocalDateTime

class CoupleCodeEvent(
    private val coupleId: Long,
    override val eventType: CoupleCodeEventType,
    override val attributes: CoupleCodeEventAttribute,
    override val createdAt: LocalDateTime,
): DomainEvent<CoupleCodeEventType, CoupleCodeEvent.CoupleCodeEventAttribute>(coupleId, eventType, attributes, createdAt) {

    data class CoupleCodeEventAttribute(
        val expiredAt: LocalDateTime,
    )
}