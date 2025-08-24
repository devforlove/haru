package com.senok.couple.adapter.`in`.consumer

import com.senok.couple.application.`in`.SaveIndividualPort
import com.senok.corecommon.type.user.UserEventType
import com.senok.coreeventpublisher.common.UserInternalEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserEventHandler(
    private val saveIndividualPort: SaveIndividualPort
) {

    @TransactionalEventListener
    fun handleBeforeTransaction(event: UserInternalEvent) {

        when (event.eventType) {
            UserEventType.REGISTER -> {}
        }
    }
}