package com.senok.apicore.user.domain.user

import com.senok.apicore.common.domain.DomainModel
import com.senok.apicore.user.domain.auth.OAuth2UserInfo
import com.senok.corecommon.types.user.GenderType
import com.senok.corecommon.types.user.UserStatus

class User(
    val email: String,
    var nickname: String,
    var profileImage: String,
    var gender: GenderType = GenderType.UNKNOWN,
    var rubyCount: Int = 0,
    var userStatus: UserStatus = UserStatus.INITIATED,
): DomainModel<User>() {
    
    init {
        require(email.isNotBlank()) { "이메일은 비어있을 수 없습니다." }
        require(nickname.isNotBlank()) { "닉네임은 비어있을 수 없습니다." }
    }
    
    companion object {
        fun initUser(
            email: String,
            nickname: String,
            profileImage: String): User {
            
            return User(
                email,
                nickname,
                profileImage
            )
        }
    }

    fun activeUser(nickname: String, gender: GenderType) {
        require(nickname.isNotBlank()) { "닉네임은 비어있을 수 없습니다." }
        this.nickname = nickname
        this.gender = gender
        this.userStatus = UserStatus.ACTIVE
    }
}