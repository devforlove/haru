package com.senok.apicore.user.application.out

import com.senok.apicore.user.domain.user.User

interface SaveUserPort {
    fun saveUser(user: User): User
}