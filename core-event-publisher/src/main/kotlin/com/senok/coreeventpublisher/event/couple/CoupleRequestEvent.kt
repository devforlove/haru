package com.senok.coreeventpublisher.event.couple

import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class CoupleRequestEvent(
    val coupleRequestId: Long,
    val _eventType: CoupleRequestEventType,
    val _attributes: Unit,
    val _createdAt: LocalDateTime,
): DomainEvent<CoupleRequestEventType, Unit>(coupleRequestId, _eventType, _attributes, _createdAt)