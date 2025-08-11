package com.senok.user.adapter.out.persistence

import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.FindUserPort
import com.senok.user.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter: FindUserPort, UpdateUserPort {

    override fun findUser(id: Long): User {
        TODO("Not yet implemented")
    }

    override fun updateUserInfo(user: User) {
        TODO("Not yet implemented")
    }
}