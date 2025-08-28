package com.senok.apicore.couple.domain.model

import com.senok.corecommon.constants.CoupleConstant
import java.time.LocalDateTime
import java.util.*

class CoupleCode(
    val coupleId: Long,
    val code: String,
    val expiredAt: LocalDateTime,
) {
    companion object {
        fun generateCode(
            coupleId: Long
        ): CoupleCode {

            return CoupleCode(
                coupleId = coupleId,
                code = UUID.randomUUID().toString(),
                expiredAt = LocalDateTime.now().plusDays(CoupleConstant.expiredDay)
            )
        }
    }
}