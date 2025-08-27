package com.senok.apicore.fixtures.domain.user

import com.senok.apicore.user.adapter.out.persistence.entity.UserEntity
import com.senok.corecommon.type.user.GenderType
import com.senok.corecommon.type.user.UserStatus

class UserEntityFixture {

    companion object {
        fun getUserEntity(
            id: Long? = null,
            email: String = "example@example.com",
            password: String? = null,
            name: String = "example",
            nickname: String = "hiho",
            profileImage: String = "https://example.com/image/1600x1600",
            gender: GenderType = GenderType.MALE,
            rubyCount: Int = 0,
            userStatus: UserStatus = UserStatus.ACTIVE
        ) = UserEntity(id, email, password, name, nickname, profileImage, gender, rubyCount, userStatus)
    }
}