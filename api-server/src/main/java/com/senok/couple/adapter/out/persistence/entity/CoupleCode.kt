package com.senok.couple.adapter.out.persistence.entity

import com.senok.common.db.entitty.BaseEntity
import com.senok.common.db.entitty.EntityId
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple_code")
@Entity
class CoupleCode(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<CoupleCode>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    val couple: Couple,

    @Column(name = "code")
    val code: String,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime,
): BaseEntity()
