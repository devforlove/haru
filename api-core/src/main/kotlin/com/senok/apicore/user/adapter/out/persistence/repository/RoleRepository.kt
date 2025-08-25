package com.senok.apicore.user.adapter.out.persistence.repository

import com.senok.apicore.user.adapter.out.persistence.entity.RoleEntity
import org.springframework.data.repository.Repository

interface RoleRepository: Repository<RoleEntity, Long> {

    fun save(role: RoleEntity)
}