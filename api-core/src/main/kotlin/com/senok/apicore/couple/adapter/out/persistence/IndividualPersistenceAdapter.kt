package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.couple.adapter.out.persistence.mapper.IndividualMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.IndividualRepository
import com.senok.apicore.couple.application.out.FindIndividualPort
import com.senok.apicore.couple.application.out.SaveIndividualPort
import com.senok.apicore.couple.domain.model.Individual
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
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

    override fun findIndividual(userId: Long): Individual {
        return individualRepository.findByUserId(userId)
            ?.let(mapper::toDomain)
            ?: throw ApiException(ErrorCode.ENTITY_NOT_FOUND, "UserId=$userId")
    }
}