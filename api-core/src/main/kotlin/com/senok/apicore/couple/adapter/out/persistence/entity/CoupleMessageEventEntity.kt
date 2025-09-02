package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.coreeventpublisher.event.couple.CoupleEventType
import com.senok.coreeventpublisher.event.couple.CoupleMessageEventType
import jakarta.persistence.*

@Table(name = "couple_message_event")
@Entity
class CoupleMessageEventEntity(

    @Column(name = "couple_message_id")
    val coupleMessageId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    val eventType: CoupleMessageEventType,

    @Column(name = "attributes")
    val attributes: String
): BaseEntity()