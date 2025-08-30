package com.senok.coreeventpublisher.event.user

import com.senok.corecommon.type.user.GenderType
import com.senok.coreeventpublisher.common.DomainEvent
import java.time.LocalDateTime

class UserEvent(
    val userId: Long,
    _eventType: UserEventType,
    _attributes: UserEventAttribute,
    _createdAt: LocalDateTime,
): DomainEvent<UserEventType, UserEvent.UserEventAttribute>(userId, _eventType, _attributes, _createdAt) {

    data class UserEventAttribute(
        val userId: Long,
        val gender: GenderType,
    )
}