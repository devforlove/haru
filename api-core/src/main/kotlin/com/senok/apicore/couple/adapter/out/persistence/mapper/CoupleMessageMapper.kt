package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEntity
import com.senok.apicore.couple.domain.model.CoupleMessage
import org.springframework.stereotype.Component

@Component
class CoupleMessageMapper: CommonMapper<CoupleMessageEntity, CoupleMessage> {

    override fun toEntity(domain: CoupleMessage): CoupleMessageEntity {
        return CoupleMessageEntity(
            domain.coupleId,
            domain.type,
            domain.status,
            domain.textFromFemale,
            domain.textFromMale,
        )
    }

    override fun toDomain(entity: CoupleMessageEntity): CoupleMessage {
        return CoupleMessage(
            entity.coupleId,
            entity.type,
            entity.status,
            entity.textFromFemale,
            entity.textFromMale,
        ).assignId(entity.id!!)
    }

}