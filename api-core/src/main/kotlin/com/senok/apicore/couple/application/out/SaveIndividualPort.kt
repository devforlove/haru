package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.Individual

interface SaveIndividualPort {
    fun saveIndividual(individual: Individual)
}