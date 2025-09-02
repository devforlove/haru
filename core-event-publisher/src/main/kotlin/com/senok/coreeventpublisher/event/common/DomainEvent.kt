package com.senok.coreeventpublisher.event.common

import java.time.LocalDateTime

open class DomainEvent<T, P : Any>(
    protected val connId: Long,
    protected val _eventType: T,
    protected val _attributes: P,
    protected val _createdAt: LocalDateTime
)