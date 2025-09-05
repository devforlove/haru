package com.senok.coreeventpublisher.event.couple

import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class CoupleMessageEvent(
    val coupleMessageId: Long,
    val eventType: CoupleMessageEventType,
    val attributes: CoupleMessageEventAttribute,
    val createdAt: LocalDateTime,
): DomainEvent<CoupleMessageEventType, CoupleMessageEvent.CoupleMessageEventAttribute>(coupleMessageId, eventType, attributes, createdAt) {
    
    data class CoupleMessageEventAttribute(
        val coupleId: Long,
        val message: String,
        val fromId: Long,
        val toId: Long
    )
}