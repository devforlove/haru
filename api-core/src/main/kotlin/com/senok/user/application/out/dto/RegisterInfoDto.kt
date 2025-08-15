package com.senok.user.application.out.dto

import com.senok.user.adapter.out.persistence.entity.GenderType

data class RegisterInfoDto(
    val userId: Long,
    val nickname: String,
    val genderType: GenderType,
)
