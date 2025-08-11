package com.senok.user.application.out

import com.senok.user.domain.user.User

interface FindUserPort {
    fun findUser(id: Long): User
}