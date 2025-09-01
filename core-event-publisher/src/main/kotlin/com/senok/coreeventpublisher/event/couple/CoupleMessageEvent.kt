package com.senok.coreeventpublisher.event.couple

import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class CoupleMessageEvent(
    val coupleMessageId: Long,
    val _eventType: CoupleMessageEventType,
    val _attributes: CoupleMessageEventAttribute,
    val _createdAt: LocalDateTime,
): DomainEvent<CoupleMessageEventType, CoupleMessageEvent.CoupleMessageEventAttribute>(coupleMessageId, _eventType, _attributes, _createdAt) {
    
    data class CoupleMessageEventAttribute(
        val coupleId: Long,
        val message: String,
        val fromId: Long,
        val toId: Long
    )
}