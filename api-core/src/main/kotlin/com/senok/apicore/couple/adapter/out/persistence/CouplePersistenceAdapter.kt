package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleRepository
import com.senok.apicore.couple.application.out.FindCouplePort
import com.senok.apicore.couple.application.out.SaveCouplePort
import com.senok.apicore.couple.domain.model.Couple
import org.springframework.stereotype.Component

@Component
class CouplePersistenceAdapter(
    private val coupleRepository: CoupleRepository,
    private val coupleMapper: CoupleMapper
): FindCouplePort, SaveCouplePort {

    override fun findCouple(femaleId: Long, maleId: Long): Couple? {
        return coupleRepository.findByFemaleIdAndMaleId(femaleId, maleId)
            ?.let { coupleMapper.toDomain(it) }
    }

    override fun saveCouple(couple: Couple): Couple {
        return coupleMapper.toEntity(couple)
            .let { coupleMapper.toDomain(coupleRepository.save(it)) }
    }
}