package com.senok.apicore.couple.domain.model

import java.time.LocalDateTime

class CoupleCode(
    val coupleId: Long,
    val code: String,
    val expiredAt: LocalDateTime,
)