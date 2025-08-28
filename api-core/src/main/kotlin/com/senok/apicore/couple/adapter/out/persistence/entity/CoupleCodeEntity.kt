package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple_code")
@Entity
class CoupleCodeEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long? = null,

    @Column(name = "couple_id")
    val coupleId: Long,

    @Column(name = "code")
    val code: String,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime,
): BaseEntity()
