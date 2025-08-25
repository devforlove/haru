package com.senok.coreeventpublisher.common

import java.time.LocalDateTime

open class DomainEvent<T, P : Any>(
    open val userId: Long,
    open val eventType: T,
    open val attributes: P,
    open val createdAt: LocalDateTime
)