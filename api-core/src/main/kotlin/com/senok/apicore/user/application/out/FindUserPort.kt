package com.senok.apicore.user.application.out

import com.senok.apicore.user.domain.user.User

interface FindUserPort {
    fun findUser(id: Long): User
}