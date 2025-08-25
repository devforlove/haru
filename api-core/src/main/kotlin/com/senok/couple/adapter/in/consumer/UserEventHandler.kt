package com.senok.couple.adapter.`in`.consumer

import com.senok.couple.application.`in`.SaveIndividualPort
import com.senok.corecommon.type.user.UserEventType
import com.senok.coreeventpublisher.user.UserEvent
import com.senok.couple.domain.model.Individual
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserEventHandler(
    private val saveIndividualPort: SaveIndividualPort
) {

    @TransactionalEventListener
    fun handleBeforeTransaction(event: UserEvent) {

        when (event.eventType) {
            UserEventType.REGISTER -> {
                saveIndividualPort.saveIndividual(
                    Individual(
                        event.attributes.userId,
                        event.attributes.gender,
                        false
                    )
                )
            }
        }
    }
}