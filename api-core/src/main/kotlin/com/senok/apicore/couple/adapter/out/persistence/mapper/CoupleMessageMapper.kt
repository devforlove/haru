package com.senok.apicore.couple.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.couple.adapter.out.persistence.entity.CoupleMessageEntity
import com.senok.apicore.couple.domain.model.CoupleMessage
import org.springframework.stereotype.Component

@Component
class CoupleMessageMapper: CommonMapper<CoupleMessageEntity, CoupleMessage> {

    override fun toEntity(domain: CoupleMessage): CoupleMessageEntity {
        return CoupleMessageEntity(
            coupleId = domain.coupleId,
            type = domain.type,
            status = domain.status,
            femaleMessageContent = CoupleMessageEntity.MessageContentValue(
                domain.contentFromFemale.userId,
                domain.contentFromFemale.message
            ),
            maleMessageContent = CoupleMessageEntity.MessageContentValue(
                domain.contentFromMale.userId,
                domain.contentFromMale.message
            ),
        )
    }

    override fun toDomain(entity: CoupleMessageEntity): CoupleMessage {
        return CoupleMessage(
            coupleId = entity.coupleId,
            type = entity.type,
            status = entity.status,
            contentFromFemale = CoupleMessage.MessageContent(
                entity.femaleMessageContent.userId,
                entity.femaleMessageContent.text,
            ),
            contentFromMale = CoupleMessage.MessageContent(
                entity.maleMessageContent.userId,
                entity.maleMessageContent.text,
            )
        ).assignId(entity.id!!)
    }
}