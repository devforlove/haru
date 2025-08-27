package com.senok.apicore.couple.adapter.out.persistence.repository

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleCodeEntity
import org.springframework.data.repository.Repository

interface CoupleCodeRepository: Repository<CoupleCodeEntity, Int> {

    fun save(entity: CoupleCodeEntity)
}