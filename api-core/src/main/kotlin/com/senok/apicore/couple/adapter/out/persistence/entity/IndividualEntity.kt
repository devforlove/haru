package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.corecommon.type.user.GenderType
import jakarta.persistence.*

@Table(name = "individual")
@Entity
class IndividualEntity(

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "gender")
    val gender: GenderType,

    @Column(name = "is_couple")
    val isCouple: Boolean,
): BaseEntity()