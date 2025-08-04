package com.senok.alarm.adapter.out.persistence.entity

import com.senok.common.db.entitty.EntityId
import jakarta.persistence.*

@Table(name = "notification_history")
@Entity
class NotificationHistory(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<NotificationHistory>,

    @Column(name = "is_published")
    val isPublished: Boolean = false,
)