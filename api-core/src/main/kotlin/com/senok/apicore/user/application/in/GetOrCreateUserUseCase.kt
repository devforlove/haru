package com.senok.apicore.user.application.`in`

import com.senok.apicore.user.application.`in`.command.GetOrCreateUserCommand
import com.senok.apicore.user.domain.user.User

interface GetOrCreateUserUseCase {
    fun getOrCreateUser(command: GetOrCreateUserCommand): User
}