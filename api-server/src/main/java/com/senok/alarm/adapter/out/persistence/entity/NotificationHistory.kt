package com.senok.alarm.adapter.out.persistence.entity

import jakarta.persistence.*

@Table(name = "notification_history")
@Entity
class NotificationHistory(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "is_published")
    val isPublished: Boolean = false,
)