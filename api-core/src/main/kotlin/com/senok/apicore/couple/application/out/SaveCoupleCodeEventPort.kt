package com.senok.apicore.couple.application.out

import com.senok.coreeventpublisher.couple.CoupleEvent

interface SaveCoupleCodeEventPort {
    fun saveCoupleCodeEvent(coupleEvent: CoupleEvent)
}