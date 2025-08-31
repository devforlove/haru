package com.senok.apicore.fixtures.domain.couple

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleEntity
import com.senok.corecommon.type.couple.CoupleStatus

class CoupleEntityFixture {

    companion object {
        fun getCoupleEntity(
            femaleId: Long = 1L,
            maleId: Long = 2L,
            coupleStatus: CoupleStatus = CoupleStatus.ACTIVE,
            messageCount: Int = 0,
        ) = CoupleEntity(maleId = maleId, femaleId = femaleId, coupleStatus = coupleStatus, messageCount = messageCount)
    }
}