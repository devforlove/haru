package com.senok.apicore.user.application.`in`.command

import com.senok.corecommon.types.user.GenderType
import com.senok.corecommon.types.user.ProviderType

data class RegisterUserCommand(
    val nickname: String,
    val genderType: GenderType,
    val phoneNumber: String,
    val providerType: ProviderType
)