package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.coreeventpublisher.couple.CoupleEventType
import jakarta.persistence.*

@Table(name = "couple_event")
@Entity
class CoupleEventEntity(

    @Column(name = "couple_id")
    val coupleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    val eventType: CoupleEventType,

    @Column(name = "attributes")
    val attributes: String
): BaseEntity()