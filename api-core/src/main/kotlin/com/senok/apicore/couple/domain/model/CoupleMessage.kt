package com.senok.apicore.couple.domain.model

import com.senok.apicore.common.domain.DomainModel
import com.senok.corecommon.types.couple.CoupleMessageStatus
import com.senok.corecommon.types.couple.CoupleMessageType
import com.senok.corecommon.types.user.GenderType
import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent
import com.senok.coreeventpublisher.event.couple.CoupleMessageEventType
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import java.time.LocalDateTime

class CoupleMessage(
    val coupleId: Long,
    val type: CoupleMessageType,
    var status: CoupleMessageStatus,
    var contentFromFemale: MessageContent,
    var contentFromMale: MessageContent,
): DomainModel<CoupleMessage>() {
    
    fun writeMessage(genderType: GenderType, message: String) {
        val (fromId, toId) = changeContent(message, genderType)

        status = CoupleMessageStatus.COMPLETING
        publishCoupleMessageEvent(message, fromId, toId)
    }
    
    private fun changeContent(message: String, genderType: GenderType): Pair<Long, Long> {
        return when (genderType) {
            GenderType.MALE -> {
                contentFromMale.applyMessage(message)
                Pair(contentFromMale.userId, contentFromFemale.userId)
            }
            GenderType.FEMALE -> {
                contentFromFemale.applyMessage(message)
                Pair(contentFromFemale.userId, contentFromMale.userId)
            }
            else -> throw ApiException(ErrorCode.CLIENT_ERROR)
        }
    }
    
    private fun publishCoupleMessageEvent(message: String, fromUserId: Long, toUserId: Long) {
        publishEvent(
            CoupleMessageEvent(
                coupleMessageId = id,
                eventType = CoupleMessageEventType.WRITE_MESSAGE,
                attributes = CoupleMessageEvent.CoupleMessageEventAttribute(
                    coupleId,
                    message,
                    fromUserId,
                    toUserId,
                ),
                createdAt = LocalDateTime.now(),
            )
        )
    }
    
    class MessageContent(val userId: Long, var message: String? = null) {
        fun applyMessage(message: String) {
            this.message = message
        }
    }
}