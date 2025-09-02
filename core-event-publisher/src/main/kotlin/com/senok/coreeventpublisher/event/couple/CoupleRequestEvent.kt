package com.senok.coreeventpublisher.event.couple

import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class CoupleRequestEvent(
    val coupleRequestId: Long,
    val eventType: CoupleRequestEventType,
    val attributes: Unit,
    val createdAt: LocalDateTime,
): DomainEvent<CoupleRequestEventType, Unit>(coupleRequestId, eventType, attributes, createdAt)