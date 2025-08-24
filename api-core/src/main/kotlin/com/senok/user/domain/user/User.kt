package com.senok.user.domain.user

import com.senok.corecommon.type.user.GenderType
import com.senok.corecommon.type.user.UserStatus

class User(
    val id: Long,
    val email: String,
    var name: String,
    var nickname: String,
    var profileImage: String,
    var gender: GenderType,
    var rubyCount: Int,
    var userStatus: UserStatus = UserStatus.INITIATED,
) {
    init {
        require(email.isNotBlank()) { "이메일은 비어있을 수 없습니다." }
        require(nickname.isNotBlank()) { "닉네임은 비어있을 수 없습니다." }
    }

    fun activeUser(nickname: String, gender: GenderType) {
        require(nickname.isNotBlank()) { "닉네임은 비어있을 수 없습니다." }
        this.nickname = nickname
        this.gender = gender

        this.userStatus = UserStatus.ACTIVE
    }
}