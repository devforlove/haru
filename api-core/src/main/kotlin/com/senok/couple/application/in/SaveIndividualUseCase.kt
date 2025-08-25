package com.senok.couple.application.`in`

import com.senok.coreeventpublisher.user.UserEvent

interface SaveIndividualUseCase {
    fun saveIndividual(event: UserEvent)
}
