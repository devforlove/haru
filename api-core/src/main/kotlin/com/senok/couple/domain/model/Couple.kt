package com.senok.couple.domain.model

import com.senok.corecommon.type.couple.CoupleStatus

class Couple(
    val femaleId: Long,
    val maleId: Long,
    val coupleStatus: CoupleStatus,
    val messageCount: Int,
) {
    companion object {
        fun initCouple(
            femaleId: Long,
            maleId: Long,
        ): Couple {

            return Couple(
                femaleId = femaleId,
                maleId = maleId,
                coupleStatus = CoupleStatus.REQUESTING,
                0
            )
        }
    }
}