package com.senok.apicore.couple.adapter.`in`.web.request

import com.senok.apicore.couple.application.`in`.command.WriteMessageCommand

data class WriteMessageRequest(
    private val coupleId: Long,
    private val coupleMessageId: Long,
    private val coupleMessage: String,
) {

    fun toCommand(userId: Long): WriteMessageCommand {
        return WriteMessageCommand(coupleId, coupleMessageId, coupleMessage, userId)
    }
}