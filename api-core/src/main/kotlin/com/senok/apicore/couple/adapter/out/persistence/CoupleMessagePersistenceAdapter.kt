package com.senok.apicore.couple.adapter.out.persistence

import com.senok.apicore.common.transaction.Tx
import com.senok.apicore.couple.adapter.out.persistence.mapper.CoupleMessageMapper
import com.senok.apicore.couple.adapter.out.persistence.repository.CoupleMessageRepository
import com.senok.apicore.couple.application.out.FindCoupleMessagePort
import com.senok.apicore.couple.application.out.WriteCoupleMessagePort
import com.senok.apicore.couple.domain.model.CoupleMessage
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class CoupleMessagePersistenceAdapter(
    private val coupleMessageRepository: CoupleMessageRepository,
    private val mapper: CoupleMessageMapper
): FindCoupleMessagePort, WriteCoupleMessagePort {

    override fun findCoupleMessage(coupleMessageId: Long): CoupleMessage {
        return coupleMessageRepository.findById(coupleMessageId)
            ?.let { return mapper.toDomain(it) }
            ?: throw ApiException(ErrorCode.ENTITY_NOT_FOUND)
    }
    
    override fun writeMessage(coupleMessage: CoupleMessage) {
        Tx.writable {
            coupleMessageRepository.findById(coupleMessage.id)?.apply {
                this.maleMessageContent.withText(coupleMessage.contentFromMale.message)
                this.femaleMessageContent.withText(coupleMessage.contentFromFemale.message)
                this.status = coupleMessage.status
            }
        }
    }
}