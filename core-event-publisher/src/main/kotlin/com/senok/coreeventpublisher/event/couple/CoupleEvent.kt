package com.senok.coreeventpublisher.event.couple

import com.senok.coreeventpublisher.common.DomainEvent
import java.time.LocalDateTime

class CoupleEvent(
    val coupleId: Long,
    val _eventType: CoupleEventType,
    val _attributes: CoupleEventAttribute,
    val _createdAt: LocalDateTime,
): DomainEvent<CoupleEventType, CoupleEvent.CoupleEventAttribute>(coupleId, _eventType, _attributes, _createdAt) {

    data class CoupleEventAttribute(
        val coupleCodeExpiredAt: LocalDateTime? = null,
    )
}