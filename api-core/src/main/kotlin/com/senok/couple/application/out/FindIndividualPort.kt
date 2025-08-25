package com.senok.couple.application.out

import com.senok.couple.domain.model.Individual

interface FindIndividualPort {
    fun findIndividual(userId: Long): Individual
}