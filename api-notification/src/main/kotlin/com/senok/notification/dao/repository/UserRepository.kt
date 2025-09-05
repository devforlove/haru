package com.senok.notification.dao.repository

import com.senok.notification.dao.entity.User
import org.springframework.data.repository.Repository

interface UserRepository: Repository<User, Long> {
    fun save(user: User)
}