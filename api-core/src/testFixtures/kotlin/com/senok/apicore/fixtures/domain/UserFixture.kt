package com.senok.apicore.fixtures.domain

import com.senok.user.adapter.out.persistence.entity.GenderType
import com.senok.user.adapter.out.persistence.entity.UserStatus
import com.senok.user.domain.user.User

class UserFixture {

    companion object {
        fun getUser(
            id: Long = 1L,
            email: String = "example@example.com",
            name: String = "example",
            nickname: String = "hiho",
            profileImage: String = "https://example.com/image/1600x1600",
            gender: GenderType = GenderType.MALE,
            rubyCount: Int = 0,
            userStatus: UserStatus = UserStatus.ACTIVE

        ) = User(id, email, name, nickname, profileImage, gender, rubyCount, userStatus)
    }
}