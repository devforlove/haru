package com.senok.apicore.couple.domain.model

import com.senok.apicore.common.domain.DomainModel
import com.senok.corecommon.types.couple.CoupleStatus

class Couple(
    val femaleId: Long,
    val maleId: Long,
    var coupleStatus: CoupleStatus,
    var messageCount: Int,
): DomainModel<Couple>() {
    
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

    fun changeStatus(status: CoupleStatus) {
        coupleStatus = status
    }
}