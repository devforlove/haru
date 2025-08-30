package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.CoupleRequest

interface SaveCoupleRequestPort {
    fun saveCoupleRequest(coupleRequest: CoupleRequest)
}