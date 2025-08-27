package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleCodeEntity
import com.senok.apicore.couple.domain.model.CoupleCode
import org.springframework.stereotype.Component

@Component
class CoupleCodeMapper: CommonMapper<CoupleCodeEntity, CoupleCode> {

    override fun toEntity(domain: CoupleCode): CoupleCodeEntity {
        TODO("Not yet implemented")
    }

    override fun toDomain(entity: CoupleCodeEntity): CoupleCode {
        TODO("Not yet implemented")
    }
}