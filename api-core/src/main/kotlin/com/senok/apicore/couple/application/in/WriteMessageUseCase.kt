package com.senok.apicore.couple.application.`in`

import com.senok.apicore.couple.application.`in`.command.WriteMessageCommand

interface WriteMessageUseCase {
    fun writeMessage(command: WriteMessageCommand)
}