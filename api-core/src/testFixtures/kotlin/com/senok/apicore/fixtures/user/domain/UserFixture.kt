package com.senok.apicore.fixtures.user.domain

import com.senok.apicore.user.domain.user.User
import com.senok.corecommon.types.user.GenderType
import com.senok.corecommon.types.user.UserStatus

class UserFixture {

    companion object {
        fun getUser(
            id: Long = 1L,
            email: String = "example@example.com",
            nickname: String = "hiho",
            profileImage: String = "https://example.com/image/1600x1600",
            gender: GenderType = GenderType.MALE,
            rubyCount: Int = 0,
            userStatus: UserStatus = UserStatus.ACTIVE
        ) = User(email, nickname, profileImage, gender, rubyCount, userStatus)
            .assignId(id)
    }
}