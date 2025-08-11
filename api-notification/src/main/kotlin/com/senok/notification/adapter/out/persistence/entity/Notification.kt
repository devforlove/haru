package com.senok.notification.adapter.out.persistence.entity


import com.senok.alarm.adapter.out.persistence.entity.NotificationStatus
import com.senok.alarm.adapter.out.persistence.entity.NotificationType
import com.senok.coredb.entity.BaseEntity
import jakarta.persistence.*

@Table(name = "notification")
@Entity
class Notification(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "sender_id")
    val senderId: Long,

    @Column(name = "receiver_id")
    val receiverId: Long,

    @Column(name = "notification_type")
    val type: NotificationType,

    @Column(name = "notification_status")
    val status: NotificationStatus,
): BaseEntity()
