package com.senok.apicore.couple.domain.model

import com.senok.corecommon.type.couple.CoupleMessageStatus
import com.senok.corecommon.type.couple.CoupleMessageType
import com.senok.corecommon.type.user.GenderType
import kotlin.properties.Delegates

class CoupleMessage(
    val coupleId: Long,
    val type: CoupleMessageType,
    var status: CoupleMessageStatus,
    var textFromFemale: String? = null,
    var textFromMale: String? = null,
) {
    fun writeMessage(genderType: GenderType, message: String) {
        if (genderType == GenderType.MALE) {
            textFromMale = message
        } else if (genderType == GenderType.FEMALE) {
            textFromFemale = message
        }
        status = CoupleMessageStatus.COMPLETING
    }
    
    var id: Long by Delegates.notNull()
        private set
    
    fun assignId(id: Long): CoupleMessage{
        this.id = id
        return this
    }
}