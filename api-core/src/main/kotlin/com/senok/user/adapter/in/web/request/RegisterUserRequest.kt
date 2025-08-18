package com.senok.user.adapter.`in`.web.request

import com.senok.user.adapter.out.persistence.entity.GenderType
import com.senok.user.adapter.out.persistence.entity.ProviderType
import com.senok.user.application.`in`.command.RegisterUserCommand

data class RegisterUserRequest(
    val nickname: String,
    val genderType: GenderType,
    val phoneNumber: String,
    val providerType: ProviderType
) {
    fun toCommand(): RegisterUserCommand {
        return RegisterUserCommand(nickname, genderType, phoneNumber, providerType)
    }
}