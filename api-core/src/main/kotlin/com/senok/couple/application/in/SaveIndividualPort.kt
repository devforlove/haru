package com.senok.couple.application.`in`

import com.senok.couple.domain.model.Individual

interface SaveIndividualPort {
    fun saveIndividual(individual: Individual)
}