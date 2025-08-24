package com.senok.couple.application.`in`

import com.senok.couple.adapter.out.persistence.entity.IndividualEntity

interface SaveIndividualPort {
    fun saveIndividual(individual: IndividualEntity)
}