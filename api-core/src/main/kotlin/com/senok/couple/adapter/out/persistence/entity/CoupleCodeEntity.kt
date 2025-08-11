package com.senok.couple.adapter.out.persistence.entity

import com.senok.coredb.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple_code")
@Entity
class CoupleCodeEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    val couple: CoupleEntity,

    @Column(name = "code")
    val code: String,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime,
): BaseEntity()
