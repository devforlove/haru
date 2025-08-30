package com.senok.apicore.couple.domain.model

import com.senok.corecommon.type.couple.CoupleRequestType

class CoupleRequest(
    val coupleId: Long,
    var coupleRequestType: CoupleRequestType,
) {
    companion object {
        fun initRequest(coupleId: Long): CoupleRequest {
            return CoupleRequest(
                coupleId = coupleId,
                coupleRequestType = CoupleRequestType.REQUESTING
            )
        }
    }

    val isRequesting = coupleRequestType == CoupleRequestType.REQUESTING

    fun changeTypeOnRequest(isAccepted: Boolean) {
        coupleRequestType = if (isAccepted) {
            CoupleRequestType.ACCEPTED
        } else {
            CoupleRequestType.REJECTED
        }
    }
}