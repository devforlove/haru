package com.senok.user.application.out

import com.senok.user.adapter.out.persistence.entity.User

interface UpdateUserPort {
    fun updateUserInfo(user: User)
}