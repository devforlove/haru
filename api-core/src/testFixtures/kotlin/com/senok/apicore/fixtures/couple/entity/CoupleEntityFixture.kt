package com.senok.apicore.fixtures.couple.entity

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleEntity
import com.senok.corecommon.types.couple.CoupleStatus
import java.time.LocalDateTime

class CoupleEntityFixture {

    companion object {
        fun getCoupleEntity(
            femaleId: Long = 1L,
            maleId: Long = 2L,
            coupleStatus: CoupleStatus = CoupleStatus.ACTIVE,
            messageCount: Int = 0,
            coupleCode: String = "JesRTe93cC4Diviaix",
            coupleCodeExpiredAt: LocalDateTime = LocalDateTime.now(),
        ) = CoupleEntity(
            maleId = maleId,
            femaleId = femaleId,
            coupleStatus = coupleStatus,
            messageCount = messageCount,
            coupleCode = coupleCode,
            coupleCodeExpiredAt = coupleCodeExpiredAt
        )
    }
}