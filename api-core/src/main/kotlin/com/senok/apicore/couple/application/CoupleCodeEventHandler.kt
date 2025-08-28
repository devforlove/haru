package com.senok.apicore.couple.application

import com.senok.apicore.common.event.Events
import com.senok.apicore.couple.adapter.out.persistence.CoupleCodeEventPersistenceAdapter
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class CoupleCodeEventHandler(
    private val coupleCodeEventPersistenceAdapter: CoupleCodeEventPersistenceAdapter
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleBeforeTransaction(event: CoupleEvent) {
        coupleCodeEventPersistenceAdapter.saveCoupleCodeEvent(event)
        Events.raise(event)
    }
}