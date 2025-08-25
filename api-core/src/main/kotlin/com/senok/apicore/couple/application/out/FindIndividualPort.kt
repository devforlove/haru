package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.Individual


interface FindIndividualPort {
    fun findIndividual(userId: Long): Individual
}