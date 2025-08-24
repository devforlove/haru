package com.senok.couple.adapter.out.persistence

import com.senok.couple.adapter.out.persistence.entity.IndividualEntity
import com.senok.couple.adapter.out.persistence.repository.IndividualRepository
import com.senok.couple.application.`in`.SaveIndividualPort
import org.springframework.stereotype.Component

@Component
class IndividualPersistenceAdapter(
    private val individualRepository: IndividualRepository
): SaveIndividualPort {

    override fun saveIndividual(individual: IndividualEntity) {
        individualRepository.save(individual)
    }
}