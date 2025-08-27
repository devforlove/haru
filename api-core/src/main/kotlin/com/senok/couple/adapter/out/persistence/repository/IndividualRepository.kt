package com.senok.couple.adapter.out.persistence.repository

import com.senok.couple.adapter.out.persistence.entity.IndividualEntity
import org.springframework.data.repository.Repository

interface IndividualRepository: Repository<IndividualEntity, Long> {

    fun save(individual: IndividualEntity)
}