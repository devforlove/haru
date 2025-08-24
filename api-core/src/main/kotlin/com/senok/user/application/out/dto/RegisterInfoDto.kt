package com.senok.user.application.out.dto

import com.senok.corecommon.type.user.GenderType

data class RegisterInfoDto(
    val userId: Long,
    val nickname: String,
    val genderType: GenderType,
)
