package com.senok.user.application.`in`.command

import com.senok.user.adapter.out.persistence.entity.GenderType
import com.senok.user.adapter.out.persistence.entity.ProviderType

data class RegisterUserCommand(
    val nickname: String,
    val genderType: GenderType,
    val deviceKey: String,
    val phoneNumber: String,
    val providerType: ProviderType
)