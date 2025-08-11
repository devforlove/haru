package com.senok.couple.adapter.out.persistence.entity

import com.senok.coredb.entity.BaseEntity
import jakarta.persistence.*

@Table(name = "couple")
@Entity
class CoupleEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "female_id")
    val femaleId: Long,

    @Column(name = "male_id")
    val maleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "couple_status")
    val coupleStatus: CoupleStatus,

    @Column(name = "message_count")
    val messageCount: MessageCount = MessageCount(0),
): BaseEntity() {

    @JvmInline
    value class MessageCount(val value: Int) {

    }
}
