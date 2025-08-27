package com.senok.couple.adapter.out.persistence

import com.senok.couple.adapter.out.persistence.mapper.IndividualMapper
import com.senok.couple.adapter.out.persistence.repository.IndividualRepository
import com.senok.couple.application.out.FindIndividualPort
import com.senok.couple.application.out.SaveIndividualPort
import com.senok.couple.domain.model.Individual
import org.springframework.stereotype.Component

@Component
class IndividualPersistenceAdapter(
    private val individualRepository: IndividualRepository,
    private val mapper: IndividualMapper
): SaveIndividualPort, FindIndividualPort {

    override fun saveIndividual(individual: Individual) {
        val individualEntity = mapper.toEntity(individual)
        individualRepository.save(individualEntity)
    }

    override fun findIndividual(userId: Long) {
        TODO("Not yet implemented")
    }
}