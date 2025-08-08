package com.senok.couple.adapter.out.persistence.entity

import com.senok.coredb.entity.BaseEntity
import com.senok.coredb.entity.EntityId
import jakarta.persistence.*

@Table(name = "couple_history")
@Entity
class CoupleMessage(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<CoupleMessage>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    val Couple: Couple,

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    val type: CoupleMessageType,

    @Enumerated(EnumType.STRING)
    @Column(name = "message_status")
    val status: CoupleMessageStatus,

    @Column(name = "text_from_female")
    val textFromFemale: String?,

    @Column(name = "text_from_male")
    val textFromMale: String?,
): BaseEntity()
