package com.senok.user.application

import com.senok.coredb.entity.EntityId
import com.senok.coredb.transaction.Tx
import com.senok.user.application.`in`.RegisterUserUseCase
import com.senok.user.application.`in`.command.RegisterUserCommand
import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.domain.user.Device
import org.springframework.stereotype.Service

@Service
class RegisterUserService(
    private val findUserPort: FindUserPort,
    private val updateUserPort: UpdateUserPort,
    private  val registerDevicePort: RegisterDevicePort
): RegisterUserUseCase {

    override fun registerUser(command: RegisterUserCommand, userId: Long) {
        Tx.run {
            val user = findUserPort.findUser(userId)

            user.activeUser(command.nickname, command.genderType)
            val device = Device.register(userId, command.deviceKey, command.providerType)

            updateUserPort.updateUserInfo(user)
            registerDevicePort.registerDevice(device)
        }
    }
}