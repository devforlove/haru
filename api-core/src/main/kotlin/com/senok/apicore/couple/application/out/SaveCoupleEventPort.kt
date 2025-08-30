package com.senok.apicore.couple.application.out

import com.senok.coreeventpublisher.event.couple.CoupleEvent

interface SaveCoupleEventPort {
    fun saveCoupleEvent(coupleEvent: CoupleEvent)
}