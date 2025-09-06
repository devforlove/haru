package com.senok.coreeventpublisher.event.couple

import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class CoupleRequestEvent(
    val coupleRequestId: Long,
    val eventType: CoupleRequestEventType,
    val attributes: CoupleRequestEventAttribute,
    val createdAt: LocalDateTime,
): DomainEvent<CoupleRequestEventType, CoupleRequestEvent.CoupleRequestEventAttribute>(coupleRequestId, eventType, attributes, createdAt) {
    
    data class CoupleRequestEventAttribute(
        val coupleId: Long
    )
}