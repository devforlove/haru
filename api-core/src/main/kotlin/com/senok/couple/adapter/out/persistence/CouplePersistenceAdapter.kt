package com.senok.couple.adapter.out.persistence

import com.senok.couple.adapter.out.persistence.mapper.CoupleMapper
import com.senok.couple.adapter.out.persistence.repository.CoupleRepository
import com.senok.couple.application.out.FindCouplePort
import com.senok.couple.application.out.SaveCouplePort
import com.senok.couple.domain.model.Couple
import org.springframework.stereotype.Component

@Component
class CouplePersistenceAdapter(
    private val coupleRepository: CoupleRepository,
    private val coupleMapper: CoupleMapper
): FindCouplePort, SaveCouplePort {

    override fun findCouple(femaleId: Long, maleId: Long): Couple {
        val coupleEntity = coupleRepository.findByFemaleIdAndMaleId(femaleId, maleId)
        return coupleMapper.toDomain(coupleEntity)
    }

    override fun saveCouple(couple: Couple): Couple {
        val coupleEntity = coupleMapper.toEntity(couple)
        coupleRepository.save(coupleEntity)
        return couple
    }
}