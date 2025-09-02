package com.senok.apicore.user.application.out.dto

import com.senok.corecommon.types.user.GenderType

data class RegisterInfoDto(
    val userId: Long,
    val nickname: String,
    val genderType: GenderType,
)
