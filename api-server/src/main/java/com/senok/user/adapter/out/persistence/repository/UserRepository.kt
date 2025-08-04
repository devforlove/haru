package com.senok.user.adapter.out.persistence.repository

import com.senok.user.adapter.out.persistence.entity.User
import org.springframework.data.repository.Repository

interface UserRepository: Repository<User, Long> {

    fun findByEmail(email: String): User?
}