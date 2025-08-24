package com.senok.user.application

import com.senok.common.event.Events
import com.senok.user.adapter.out.persistence.UserEventPersistenceAdapter
import com.senok.corecommon.type.user.UserEventType
import com.senok.coreeventpublisher.common.UserInternalEvent
import com.senok.user.domain.user.UserRegisterEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class UserRegisterEventHandler(
    private val userEventPersistenceAdapter: UserEventPersistenceAdapter
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleBeforeTransaction(event: UserRegisterEvent) {
        userEventPersistenceAdapter.saveUserRegisterEvent(event)
        Events.raise(
            UserInternalEvent(
            userId = event.userId,
            eventType = UserEventType.REGISTER,
            attributes = event.attributes)
        )
    }
}