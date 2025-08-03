package com.senok.couple.adapter.out.persistence.entity

import com.senok.common.db.entitty.BaseEntity
import jakarta.persistence.*

@Table(name = "couple_event")
@Entity
class CoupleEvent(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "couple_id")
    val coupleId: Long,

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    val eventType: CoupleEventType,

    @Column(name = "event_data")
    val data: String
): BaseEntity() {

    companion object {
        
    }

    sealed class CoupleEventData(data: String) {

    }
}
