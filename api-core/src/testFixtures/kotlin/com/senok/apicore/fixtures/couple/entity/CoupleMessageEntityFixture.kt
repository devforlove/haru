package com.senok.apicore.fixtures.couple.entity

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleEntity
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEntity
import com.senok.corecommon.type.couple.CoupleMessageStatus
import com.senok.corecommon.type.couple.CoupleMessageType

class CoupleMessageEntityFixture {
    
    companion object {
        fun getCoupleMessageEntity(
            coupleId: Long,
            type: CoupleMessageType,
            status: CoupleMessageStatus,
            textFromFemale: String? = null,
            textFromMale: String? = null,
        ) = CoupleMessageEntity(
            coupleId,
            type,
            status,
            textFromFemale,
            textFromMale,
        )
    }
}