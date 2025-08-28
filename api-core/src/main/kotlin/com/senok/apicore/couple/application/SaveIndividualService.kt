package com.senok.apicore.couple.application

import com.senok.apicore.couple.application.`in`.SaveIndividualUseCase
import com.senok.apicore.couple.application.out.SaveIndividualPort
import com.senok.apicore.couple.domain.model.Individual
import com.senok.coreeventpublisher.event.user.UserEvent
import org.springframework.stereotype.Service

@Service
class SaveIndividualService(
    private val saveIndividualPort: SaveIndividualPort
): SaveIndividualUseCase {

    override fun saveIndividual(event: UserEvent) {
        saveIndividualPort.saveIndividual(
            Individual(
                event.attributes.userId,
                event.attributes.gender,
                false
            )
        )
    }
}