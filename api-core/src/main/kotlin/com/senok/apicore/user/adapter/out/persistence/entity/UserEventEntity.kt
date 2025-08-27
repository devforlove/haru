package com.senok.apicore.user.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.coreeventpublisher.user.UserEventType
import jakarta.persistence.*

@Table(name = "user_event")
@Entity
class UserEventEntity(

    @Column(name = "user_id")
    val userId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    val eventType: UserEventType,

    @Column(name = "attributes")
    val attributes: String
): BaseEntity()