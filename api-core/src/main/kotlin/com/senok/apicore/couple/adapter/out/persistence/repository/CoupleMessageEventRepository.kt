package com.senok.apicore.couple.adapter.out.persistence.repository

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEventEntity
import org.springframework.data.repository.Repository

interface CoupleMessageEventRepository: Repository<CoupleMessageEventEntity, Long> {
    fun save(entity: CoupleMessageEventEntity)
}