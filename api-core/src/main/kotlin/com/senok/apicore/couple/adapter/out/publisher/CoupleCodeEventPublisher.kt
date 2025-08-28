package com.senok.apicore.couple.adapter.out.publisher

import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoupleCodeEventPublisher {

    @TransactionalEventListener
    fun handleInternalEvent(event: CoupleEvent) {

    }
}
