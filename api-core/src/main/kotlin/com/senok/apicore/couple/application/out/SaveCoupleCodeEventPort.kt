package com.senok.apicore.couple.application.out

import com.senok.coreeventpublisher.event.couple.CoupleEvent

interface SaveCoupleCodeEventPort {
    fun saveCoupleCodeEvent(coupleEvent: CoupleEvent)
}