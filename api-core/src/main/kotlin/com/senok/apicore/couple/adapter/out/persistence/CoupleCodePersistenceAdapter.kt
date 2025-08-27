package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleCodeMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleCodeRepository
import com.senok.apicore.couple.application.out.SaveCoupleCodePort
import com.senok.apicore.couple.domain.model.CoupleCode
import org.springframework.stereotype.Component

@Component
class CoupleCodePersistenceAdapter(
    private val coupleCodeRepository: CoupleCodeRepository,
    private val mapper: CoupleCodeMapper
): SaveCoupleCodePort {

    override fun saveCoupleCode(coupleCode: CoupleCode) {
        mapper.toEntity(coupleCode).let {
            coupleCodeRepository.save(it)
        }
    }
}