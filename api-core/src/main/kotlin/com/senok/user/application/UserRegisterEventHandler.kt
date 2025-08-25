package com.senok.user.application

import com.senok.common.event.Events
import com.senok.user.adapter.out.persistence.UserEventPersistenceAdapter
import com.senok.coreeventpublisher.user.UserEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class UserRegisterEventHandler(
    private val userEventPersistenceAdapter: UserEventPersistenceAdapter
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleBeforeTransaction(event: UserEvent) {
        userEventPersistenceAdapter.saveUserRegisterEvent(event)
        Events.raise(event)
    }
}