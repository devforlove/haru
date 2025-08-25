package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.corecommon.type.couple.CoupleStatus
import jakarta.persistence.*

@Table(name = "couple")
@Entity
class CoupleEntity(

    @Column(name = "female_id")
    val femaleId: Long,

    @Column(name = "male_id")
    val maleId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "couple_status")
    val coupleStatus: CoupleStatus,

    @Column(name = "message_count")
    val messageCount: Int,
): BaseEntity()
