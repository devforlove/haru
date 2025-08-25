package com.senok.apicore.user.application.`in`

import com.senok.apicore.user.application.`in`.command.RegisterUserCommand

interface RegisterUserUseCase {
    fun registerUser(command: com.senok.apicore.user.application.`in`.command.RegisterUserCommand, userId: Long)
}