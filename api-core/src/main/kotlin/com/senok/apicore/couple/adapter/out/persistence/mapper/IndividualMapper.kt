package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.IndividualEntity
import com.senok.apicore.couple.domain.model.Individual
import org.springframework.stereotype.Component

@Component
class IndividualMapper: CommonMapper<IndividualEntity, Individual> {

    override fun toEntity(domain: Individual): IndividualEntity {
        return IndividualEntity(
            userId = domain.userId,
            gender = domain.gender,
            isCouple = domain.isCouple,
        )
    }

    override fun toDomain(entity: IndividualEntity): Individual {
        return Individual(
            userId = entity.userId,
            gender = entity.gender,
            isCouple = entity.isCouple,
        )
    }
}