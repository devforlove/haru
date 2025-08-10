package com.senok.user.application.out

import com.senok.coredb.entity.EntityId
import com.senok.user.adapter.out.persistence.entity.User

interface FindUserPort {
    fun findUser(id: EntityId<User>): User
}