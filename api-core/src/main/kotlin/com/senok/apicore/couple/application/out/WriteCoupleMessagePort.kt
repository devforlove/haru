package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.CoupleMessage

interface WriteCoupleMessagePort {
    fun writeMessage(coupleMessage: CoupleMessage)
}