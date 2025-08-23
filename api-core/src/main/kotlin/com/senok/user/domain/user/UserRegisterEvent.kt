package com.senok.user.domain.user

import com.senok.user.adapter.out.persistence.entity.GenderType

data class UserRegisterEvent(
    val userId: Long,
    val gender: GenderType,
) {
    val attributes = UserRegisterAttributes(
        userId,
        gender
    ).toString()

    data class UserRegisterAttributes(
        val userId: Long,
        val gender: GenderType
    )
}