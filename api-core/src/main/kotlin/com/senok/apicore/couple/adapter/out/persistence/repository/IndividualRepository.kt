package com.senok.apicore.couple.adapter.out.persistence.repository

import com.senok.apicore.couple.adapter.out.persistence.entity.IndividualEntity
import org.springframework.data.repository.Repository

interface IndividualRepository: Repository<IndividualEntity, Long> {

    fun save(individual: IndividualEntity)
    fun findByUserId(userId: Long): IndividualEntity?
}