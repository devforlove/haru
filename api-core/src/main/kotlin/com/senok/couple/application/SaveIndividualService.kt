package com.senok.couple.application

import com.senok.coreeventpublisher.user.UserEvent
import com.senok.couple.application.`in`.SaveIndividualUseCase
import com.senok.couple.application.out.SaveIndividualPort
import com.senok.couple.domain.model.Individual
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