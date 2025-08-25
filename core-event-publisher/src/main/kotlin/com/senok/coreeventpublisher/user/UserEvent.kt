package com.senok.coreeventpublisher.user

import com.senok.corecommon.type.user.GenderType
import com.senok.corecommon.type.user.UserEventType
import com.senok.coreeventpublisher.common.DomainEvent
import java.time.LocalDateTime

data class UserEvent(
    override val userId: Long,
    override val eventType: UserEventType,
    override val attributes: UserEventAttribute,
    override val createdAt: LocalDateTime,
): DomainEvent<UserEventType, UserEvent.UserEventAttribute>(userId, eventType, attributes, createdAt) {

    data class UserEventAttribute(
        val userId: Long,
        val gender: GenderType,
    )
}