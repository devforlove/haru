package com.senok.apicore.user.application

import com.senok.apicore.user.application.`in`.GetOrCreateUserUseCase
import com.senok.apicore.user.application.`in`.command.GetOrCreateUserCommand
import com.senok.apicore.user.application.out.FindUserPort
import com.senok.apicore.user.application.out.SaveUserPort
import com.senok.apicore.user.domain.user.User
import org.springframework.stereotype.Service

@Service
class GetOrCreateUserService(
    private val findUserPort: FindUserPort,
    private val saveUserPort: SaveUserPort
): GetOrCreateUserUseCase {
    
    override fun getOrCreateUser(command: GetOrCreateUserCommand): User {
        return findUserPort.findUserByEmail(command.email)
            ?: saveUser(command)
    }

    private fun saveUser(command: GetOrCreateUserCommand): User {
        return saveUserPort.saveUser(User.initUser(
            command.email,
            command.nickname,
            command.profile
        ))
    }
}