package com.senok.apicore.fixtures.couple.entity

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleRequestEntity
import com.senok.corecommon.type.couple.CoupleRequestType

class CoupleRequestEntityFixture {

    companion object {
        fun getCoupleRequestEntity(
            coupleId: Long,
            coupleRequestType: CoupleRequestType = CoupleRequestType.REQUESTING,
        ) = CoupleRequestEntity(coupleId, coupleRequestType)
    }
}