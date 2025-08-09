package com.senok.user.application

import com.senok.user.application.`in`.RegisterUserUseCase
import com.senok.user.application.`in`.command.RegisterUserCommand
import org.springframework.stereotype.Service

@Service
class RegisterUserService: RegisterUserUseCase {
    override fun registerUser(command: RegisterUserCommand) {

    }
}