package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.common.transaction.Tx
import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleRepository
import com.senok.apicore.couple.application.out.ChangeCouplePort
import com.senok.apicore.couple.application.out.FindCouplePort
import com.senok.apicore.couple.application.out.SaveCouplePort
import com.senok.apicore.couple.domain.model.Couple
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class CouplePersistenceAdapter(
    private val coupleRepository: CoupleRepository,
    private val coupleMapper: CoupleMapper
): FindCouplePort, SaveCouplePort, ChangeCouplePort {

    override fun findCoupleByUserId(femaleId: Long, maleId: Long): Couple? {
        return coupleRepository.findByFemaleIdAndMaleId(femaleId, maleId)
            ?.let { coupleMapper.toDomain(it) }
    }

    override fun findCoupleByCoupleId(coupleId: Long): Couple {
        return coupleRepository.findById(coupleId)
            ?.let { coupleMapper.toDomain(it) }
            ?: throw ApiException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun saveCouple(couple: Couple): Couple {
        return coupleMapper.toEntity(couple)
            .let { coupleMapper.toDomain(coupleRepository.save(it)) }
    }

    override fun changeCoupleStatus(couple: Couple) {
        Tx.writable { 
            coupleRepository.findById(couple.coupleId).apply { 
                this?.coupleStatus = couple.coupleStatus
            }
        }
    }
}