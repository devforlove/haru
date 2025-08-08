package com.senok.couple.adapter.out.persistence.entity

import com.senok.coredb.entity.BaseEntity
import com.senok.coredb.entity.EntityId
import jakarta.persistence.*

@Table(name = "individual")
@Entity
class Individual(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<Individual>,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "gender")
    val gender: String,

    @Column(name = "is_couple")
    val isCouple: Boolean,
): BaseEntity()