package com.senok.apicore.fixtures.couple.entity

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEntity
import com.senok.corecommon.types.couple.CoupleMessageStatus
import com.senok.corecommon.types.couple.CoupleMessageType

class CoupleMessageEntityFixture {
    
    companion object {
        fun getCoupleMessageEntity(
            coupleId: Long,
            type: CoupleMessageType,
            status: CoupleMessageStatus,
            femaleUserId: Long = 2L,
            textFromFemale: String? = null,
            maleUserId: Long = 1L,
            textFromMale: String? = null,
        ) = CoupleMessageEntity(
            coupleId = coupleId,
            type = type,
            status = status,
            femaleMessageContent = CoupleMessageEntity.MessageContentValue(
                femaleUserId,
                textFromFemale
            ),
            maleMessageContent = CoupleMessageEntity.MessageContentValue(
                maleUserId,
                textFromMale
            )
        )
    }
}