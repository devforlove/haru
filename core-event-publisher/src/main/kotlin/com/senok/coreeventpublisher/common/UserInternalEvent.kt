package com.senok.coreeventpublisher.common

import com.senok.corecommon.type.user.UserEventType

data class UserInternalEvent(
    val userId: Long,
    val eventType: UserEventType,
    val attributes: String
)