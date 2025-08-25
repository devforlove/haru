package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.corecommon.type.couple.CoupleEventType
import jakarta.persistence.*

@Table(name = "couple_event")
@Entity
class CoupleEventEntity(

    @Column(name = "couple_id")
    val coupleId: Long,

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    val eventType: CoupleEventType,

    @Column(name = "event_data")
    val data: String
): BaseEntity()
