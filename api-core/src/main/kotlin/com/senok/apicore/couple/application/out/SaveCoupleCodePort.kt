package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.CoupleCode

interface SaveCoupleCodePort {
    fun saveCoupleCode(coupleCode: CoupleCode)
}