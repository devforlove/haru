package com.senok.apicore.couple.application.`in`

import com.senok.coreeventpublisher.event.user.UserEvent

interface SaveIndividualUseCase {
    fun saveIndividual(event: UserEvent)
}
