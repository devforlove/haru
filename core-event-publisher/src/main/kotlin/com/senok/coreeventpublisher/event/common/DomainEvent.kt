package com.senok.coreeventpublisher.common

import java.time.LocalDateTime

open class DomainEvent<T, P : Any>(
    val connId: Long,
    val eventType: T,
    val attributes: P,
    val createdAt: LocalDateTime
)