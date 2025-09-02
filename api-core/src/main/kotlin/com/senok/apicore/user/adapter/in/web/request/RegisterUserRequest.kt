package com.senok.apicore.user.adapter.`in`.web.request

import com.senok.corecommon.types.user.GenderType
import com.senok.corecommon.types.user.ProviderType

data class RegisterUserRequest(
    val nickname: String,
    val genderType: GenderType,
    val phoneNumber: String,
    val providerType: ProviderType
) {
    fun toCommand(): com.senok.apicore.user.application.`in`.command.RegisterUserCommand {
        return com.senok.apicore.user.application.`in`.command.RegisterUserCommand(
            nickname,
            genderType,
            phoneNumber,
            providerType
        )
    }
}