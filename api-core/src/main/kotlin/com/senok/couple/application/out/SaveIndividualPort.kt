package com.senok.couple.application.out

import com.senok.couple.domain.model.Individual

interface SaveIndividualPort {
    fun saveIndividual(individual: Individual)
}