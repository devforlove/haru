package com.senok.apicore.couple.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "couple_code")
@Entity
class CoupleCodeEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    val couple: CoupleEntity,

    @Column(name = "code")
    val code: String,

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime,
): BaseEntity() {

    @PostPersist
    private fun onPostCreate() {
        // Events.raise()
    }
}
