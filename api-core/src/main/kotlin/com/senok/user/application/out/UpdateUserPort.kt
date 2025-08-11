package com.senok.user.application.out

import com.senok.user.domain.user.User

interface UpdateUserPort {
    fun updateUserInfo(user: User)
}