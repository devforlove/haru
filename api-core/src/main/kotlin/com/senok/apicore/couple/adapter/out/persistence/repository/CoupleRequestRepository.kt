package com.senok.apicore.couple.adapter.out.persistence.repository

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleRequestEntity
import com.senok.apicore.couple.domain.model.CoupleRequest
import org.springframework.data.repository.Repository

interface CoupleRequestRepository: Repository<CoupleRequestEntity, Long> {
    fun findById(requestId: Long): CoupleRequestEntity?
    fun save(entity: CoupleRequestEntity)
}