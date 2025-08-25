package com.senok.couple.adapter.out.persistence.mapper

import com.senok.common.entity.mapper.CommonMapper
import com.senok.couple.adapter.out.persistence.entity.CoupleEntity
import com.senok.couple.domain.model.Couple
import org.springframework.stereotype.Component

@Component
class CoupleMapper: CommonMapper<CoupleEntity, Couple> {

    override fun toEntity(domain: Couple): CoupleEntity {
        TODO("Not yet implemented")
    }

    override fun toDomain(entity: CoupleEntity): Couple {
        TODO("Not yet implemented")
    }
}