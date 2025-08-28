package com.senok.apicore.user.application

import com.senok.apicore.common.event.Events
import com.senok.apicore.user.adapter.out.persistence.UserEventPersistenceAdapter
import com.senok.coreeventpublisher.user.UserEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class UserEventHandler(
    private val userEventPersistenceAdapter: UserEventPersistenceAdapter
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleBeforeTransaction(event: UserEvent) {
        userEventPersistenceAdapter.saveUserEvent(event)
        Events.raise(event)
    }
}