package com.senok.user.application.`in`.command

import com.senok.corecommon.type.user.GenderType
import com.senok.corecommon.type.user.ProviderType

data class RegisterUserCommand(
    val nickname: String,
    val genderType: GenderType,
    val phoneNumber: String,
    val providerType: ProviderType
)