package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleCodeEntity
import com.senok.apicore.couple.domain.model.CoupleCode
import org.springframework.stereotype.Component

@Component
class CoupleCodeMapper: CommonMapper<CoupleCodeEntity, CoupleCode> {

    override fun toEntity(domain: CoupleCode): CoupleCodeEntity {
        return CoupleCodeEntity(
            domain.coupleId,
            domain.code,
            domain.expiredAt
        )
    }

    override fun toDomain(entity: CoupleCodeEntity): CoupleCode {
        return CoupleCode(
            entity.coupleId,
            entity.code,
            entity.expiredAt
        )
    }
}