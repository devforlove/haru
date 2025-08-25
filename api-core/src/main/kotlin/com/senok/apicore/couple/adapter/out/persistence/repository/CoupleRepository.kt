package com.senok.apicore.couple.adapter.out.persistence.repository

import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleEntity
import org.springframework.data.repository.Repository

interface CoupleRepository: Repository<CoupleEntity, Long> {

    fun save(couple: CoupleEntity): CoupleEntity
    fun findByFemaleIdAndMaleId(femaleId: Long, maleId: Long): CoupleEntity?
}