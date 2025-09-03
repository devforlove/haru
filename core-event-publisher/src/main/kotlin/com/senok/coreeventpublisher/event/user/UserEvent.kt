package com.senok.coreeventpublisher.event.user

import com.senok.corecommon.types.user.GenderType
import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class UserEvent(
    val userId: Long,
    val eventType: UserEventType,
    val attributes: UserEventAttribute,
    val createdAt: LocalDateTime,
): DomainEvent<UserEventType, UserEvent.UserEventAttribute>(userId, eventType, attributes, createdAt) {

    data class UserEventAttribute(
        val userId: Long,
        val gender: GenderType,
        val email: String
    )
}