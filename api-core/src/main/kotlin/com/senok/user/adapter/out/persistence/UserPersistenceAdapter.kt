package com.senok.user.adapter.out.persistence

import com.senok.coredb.entity.EntityId
import com.senok.user.adapter.out.persistence.entity.User
import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.FindUserPort
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter: FindUserPort, UpdateUserPort {

    override fun findUser(id: EntityId<User>): User {
        TODO("Not yet implemented")
    }

    override fun updateUserInfo() {
        TODO("Not yet implemented")
    }
}