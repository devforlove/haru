package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleRequestEntity
import com.senok.apicore.couple.domain.model.CoupleRequest
import org.springframework.stereotype.Component

@Component
class CoupleRequestMapper: CommonMapper<CoupleRequestEntity, CoupleRequest> {

    override fun toEntity(domain: CoupleRequest): CoupleRequestEntity {
        return CoupleRequestEntity(
            domain.coupleId,
            domain.coupleRequestType
        )
    }

    override fun toDomain(entity: CoupleRequestEntity): CoupleRequest {
        return CoupleRequest(
            entity.coupleId,
            entity.coupleRequestType
        ).assignId(entity.id!!)
    }
}