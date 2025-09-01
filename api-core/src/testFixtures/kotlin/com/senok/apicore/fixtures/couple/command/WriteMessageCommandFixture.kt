package com.senok.apicore.fixtures.couple.command

import com.senok.apicore.couple.application.`in`.command.WriteMessageCommand

class WriteMessageCommandFixture {
    
    companion object {
        fun getCommand(
            coupleId: Long,
            coupleMessageId: Long,
            coupleMessage: String,
            userId: Long,
        ) =
            WriteMessageCommand(
                coupleId,
                coupleMessageId,
                coupleMessage,
                userId
            )
    }
}