package com.senok.notification.dao.entity

import com.senok.corecommon.types.noti.NotificationStatus
import com.senok.corecommon.types.noti.NotificationType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "users")
class Notification(
    
    @Id
    val id: Long? = null,
    val senderId: Long,
    val receiverId: Long,
    val type: NotificationType,
    val status: NotificationStatus,
)
