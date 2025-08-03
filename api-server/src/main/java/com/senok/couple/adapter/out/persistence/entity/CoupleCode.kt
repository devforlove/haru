package com.senok.couple.adapter.out.persistence.entity

import com.senok.common.db.entitty.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple_code")
@Entity
class CoupleCode(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "couple_id")
    val couple: Couple,

    @Column(name = "code")
    val code: String,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime,
): BaseEntity()
