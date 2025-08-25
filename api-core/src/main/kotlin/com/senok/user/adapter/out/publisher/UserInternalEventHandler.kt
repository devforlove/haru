package com.senok.user.adapter.out.publisher

import com.senok.coreeventpublisher.user.UserEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserInternalEventHandler {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleInternalEvent(event: UserEvent) {

    }
}