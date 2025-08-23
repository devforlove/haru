package com.senok.user.application

import com.senok.user.adapter.out.persistence.UserEventPersistenceAdapter
import com.senok.user.domain.user.UserRegisterEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class UserRegisterEventHandler(
    private val userEventPersistenceAdapter: UserEventPersistenceAdapter
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handle(event: UserRegisterEvent) {
        userEventPersistenceAdapter.saveUserRegisterEvent(event)
    }
}