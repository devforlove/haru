package com.senok.apicore.couple.adapter.out.persistence.repository

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEntity
import org.springframework.data.repository.Repository

interface CoupleMessageRepository: Repository<CoupleMessageEntity, Long> {
    fun save(coupleMessage: CoupleMessageEntity)
    fun findById(coupleId: Long): CoupleMessageEntity?
}