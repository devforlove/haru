package com.senok.user.application

import com.senok.common.transaction.Tx
import com.senok.user.application.`in`.RegisterUserUseCase
import com.senok.user.application.`in`.command.RegisterUserCommand
import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.application.out.dto.RegisterInfoDto
import com.senok.user.domain.user.Device
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RegisterUserService(
    private val findUserPort: FindUserPort,
    private val updateUserPort: UpdateUserPort,
    private  val registerDevicePort: RegisterDevicePort
): RegisterUserUseCase {


    @Transactional
    override fun registerUser(command: RegisterUserCommand, userId: Long) {
        val user = findUserPort.findUser(userId)

        user.activeUser(command.nickname, command.genderType)
        val device = Device.register(userId, UUID.randomUUID().toString(), command.providerType)

        updateUserPort.updateRegisterInfo(RegisterInfoDto(user.id, user.nickname, user.gender))
        registerDevicePort.registerDevice(device)

    }
}