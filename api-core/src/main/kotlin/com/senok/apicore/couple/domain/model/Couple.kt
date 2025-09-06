package com.senok.apicore.couple.domain.model

import com.senok.apicore.common.domain.DomainModel
import com.senok.corecommon.constants.CoupleConstant
import com.senok.corecommon.types.couple.CoupleStatus
import java.time.LocalDateTime
import java.util.*

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
                messageCount = 0,
            )
        }
    }

    fun changeStatus(status: CoupleStatus) {
        coupleStatus = status
    }
    
    fun makeCoupleCode(): String {
        return UUID.randomUUID().toString()
    }
    
    fun getCoupleCodeExpiredAt(): LocalDateTime {
        return LocalDateTime.now().plusDays(CoupleConstant.expiredDay)
    }
}