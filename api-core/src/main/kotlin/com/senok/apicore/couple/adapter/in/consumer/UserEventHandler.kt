package com.senok.apicore.couple.adapter.`in`.consumer

import com.senok.apicore.couple.application.`in`.SaveIndividualUseCase
import com.senok.coreeventpublisher.event.user.UserEventType
import com.senok.coreeventpublisher.event.user.UserEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserEventHandler(
    private val saveIndividualUseCase: SaveIndividualUseCase
) {

    @TransactionalEventListener
    fun handleBeforeTransaction(event: UserEvent) {

        when (event.eventType) {
            UserEventType.REGISTER -> {
                saveIndividualUseCase.saveIndividual(event)
            }
        }
    }
}