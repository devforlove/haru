package com.senok.notification.dao.entity

import com.senok.corecommon.types.couple.CoupleStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Couple(
    @Column(name = "female_id")
    val femaleId: Long,
    @Column(name = "male_id")
    val maleId: Long,
    @Column(name = "couple_code")
    val coupleCode: String,
    @Column(name = "couple_code_expired_at")
    val coupleCodeExpiredAt: LocalDateTime,
    @Column(name = "couple_status")
    val coupleStatus: CoupleStatus,
): BaseEntity()