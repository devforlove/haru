package com.senok.user.application.`in`

import com.senok.user.application.`in`.command.RegisterUserCommand

interface RegisterUserUseCase {
    fun registerUser(command: RegisterUserCommand, userId: Long)
}