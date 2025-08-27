package com.senok.apicore.couple.domain.model

import com.senok.corecommon.type.couple.CoupleStatus
import kotlin.properties.Delegates

class Couple(
    val femaleId: Long,
    val maleId: Long,
    val coupleStatus: CoupleStatus,
    val messageCount: Int,
) {
    var coupleId: Long by Delegates.notNull()
        private set

    fun assignId(id: Long): Couple {
        coupleId = id
        return this
    }

    companion object {
        fun initCouple(
            femaleId: Long,
            maleId: Long,
        ): Couple {

            return Couple(
                femaleId = femaleId,
                maleId = maleId,
                coupleStatus = CoupleStatus.REQUESTING,
                messageCount = 0
            )
        }
    }
}