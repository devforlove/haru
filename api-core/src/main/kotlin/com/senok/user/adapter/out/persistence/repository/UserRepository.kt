package com.senok.user.adapter.out.persistence.repository

import com.senok.user.adapter.out.persistence.entity.UserEntity
import org.springframework.data.repository.Repository

interface UserRepository: Repository<UserEntity, Long> {

    fun findByEmail(email: String): UserEntity?
    fun save(user: UserEntity): UserEntity
    fun findById(id: Long): UserEntity?
}