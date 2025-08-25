package com.senok.apicore.user.adapter.out.persistence.repository

import com.senok.apicore.user.adapter.out.persistence.entity.DeviceEntity
import org.springframework.data.repository.Repository

interface DeviceRepository: Repository<DeviceEntity, Long> {

    fun save(device: DeviceEntity)
    fun findByUserId(userId: Long): List<DeviceEntity>
}