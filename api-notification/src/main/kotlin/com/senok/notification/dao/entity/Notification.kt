package com.senok.notification.dao.entity

import com.senok.corecommon.types.noti.NotificationStatus
import com.senok.corecommon.types.noti.NotificationType
import jakarta.persistence.Entity

@Entity
class Notification(
    val senderId: Long,
    val receiverId: Long,
    val type: NotificationType,
    val status: NotificationStatus,
): BaseEntity()
