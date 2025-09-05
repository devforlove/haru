package com.senok.alarm.adapter.out.persistence.entity

import com.senok.notification.dao.entity.BaseEntity
import jakarta.persistence.*

@Table(name = "notification_history")
@Entity
class NotificationHistorie(
    @Column(name = "is_published")
    val isPublished: Boolean = false,
): BaseEntity()