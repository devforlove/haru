package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.CoupleMessage

interface FindCoupleMessagePort {
    fun findCoupleMessage(coupleMessageId: Long): CoupleMessage
}