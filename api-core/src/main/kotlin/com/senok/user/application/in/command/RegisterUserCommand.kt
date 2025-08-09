package com.senok.user.application.`in`.command

import com.senok.user.adapter.out.persistence.entity.GenderType

data class RegisterUserCommand(
    val nickname: String,
    val genderType: GenderType,
    val phoneNumber: String,
)