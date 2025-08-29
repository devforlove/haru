package com.senok.apicore.couple.application

import com.senok.apicore.common.event.Events
import com.senok.apicore.couple.adapter.out.persistence.CoupleEventPersistenceAdapter
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class CoupleEventHandler(
    private val coupleEventPersistenceAdapter: CoupleEventPersistenceAdapter
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleBeforeTransaction(event: CoupleEvent) {
        coupleEventPersistenceAdapter.saveCoupleCodeEvent(event)
        Events.raise(event)
    }
}