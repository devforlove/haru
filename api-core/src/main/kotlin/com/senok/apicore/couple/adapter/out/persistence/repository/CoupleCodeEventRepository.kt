package com.senok.apicore.couple.adapter.out.persistence.repository

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleEventEntity
import org.springframework.data.repository.Repository

interface CoupleCodeEventRepository: Repository<CoupleEventEntity, Long> {
    fun save(coupleEventEntity: CoupleEventEntity)
}