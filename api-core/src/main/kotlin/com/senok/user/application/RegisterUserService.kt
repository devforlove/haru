package com.senok.user.application

import com.senok.coredb.entity.EntityId
import com.senok.user.application.`in`.RegisterUserUseCase
import com.senok.user.application.`in`.command.RegisterUserCommand
import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.FindUserPort
import org.springframework.stereotype.Service

@Service
class RegisterUserService(
    private val updateUserPort: UpdateUserPort,
    private val findUserPort: FindUserPort,
    userPort: FindUserPort,
    port: UpdateUserPort
): RegisterUserUseCase {

    override fun registerUser(command: RegisterUserCommand, userId: Long) {

        val user = findUserPort.findUser(EntityId(userId))

        user.updateRegisterInfo(command.nickname, command.genderType, command.providerType, command.phoneNumber)

        updateUserPort.updateUserInfo(user)
    }
}