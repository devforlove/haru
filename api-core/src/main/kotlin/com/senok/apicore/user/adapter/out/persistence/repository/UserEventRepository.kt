package com.senok.apicore.user.adapter.out.persistence.repository

import com.senok.apicore.user.adapter.out.persistence.entity.UserEventEntity
import org.springframework.data.repository.Repository

interface UserEventRepository: Repository<UserEventEntity, Long> {
    fun save(entity: UserEventEntity)
    fun findByUserId(userId: Long): List<UserEventEntity>
}