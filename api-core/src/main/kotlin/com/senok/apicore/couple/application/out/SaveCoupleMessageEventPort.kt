package com.senok.apicore.couple.application.out

import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent

interface SaveCoupleMessageEventPort {
    fun saveCoupleMessageEvent(coupleMessageEvent: CoupleMessageEvent)
}