package com.senok.couple.adapter.out.persistence.entity

import com.senok.common.entity.BaseEntity
import com.senok.corecommon.type.couple.CoupleMessageStatus
import com.senok.corecommon.type.couple.CoupleMessageType
import jakarta.persistence.*

@Table(name = "couple_history")
@Entity
class CoupleMessageEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    val Couple: CoupleEntity,

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
