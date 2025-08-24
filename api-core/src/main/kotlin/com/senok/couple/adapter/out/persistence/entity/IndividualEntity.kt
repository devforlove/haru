package com.senok.couple.adapter.out.persistence.entity

import com.senok.common.entity.BaseEntity
import jakarta.persistence.*

@Table(name = "individual")
@Entity
class IndividualEntity(

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "gender")
    val gender: String,

    @Column(name = "is_couple")
    val isCouple: Boolean,
): BaseEntity()