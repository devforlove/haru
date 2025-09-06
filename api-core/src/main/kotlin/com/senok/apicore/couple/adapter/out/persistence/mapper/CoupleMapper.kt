package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleEntity
import com.senok.apicore.couple.domain.model.Couple
import org.springframework.stereotype.Component

@Component
class CoupleMapper: CommonMapper<CoupleEntity, Couple> {

    override fun toEntity(domain: Couple): CoupleEntity {
        return CoupleEntity(
            domain.femaleId,
            domain.maleId,
            domain.coupleStatus,
            domain.messageCount,
            domain.makeCoupleCode(),
            domain.getCoupleCodeExpiredAt()
        )
    }

    override fun toDomain(entity: CoupleEntity): Couple {
        return Couple(
            entity.femaleId,
            entity.maleId,
            entity.coupleStatus,
            entity.messageCount,
        ).assignId(entity.id!!)
    }
}