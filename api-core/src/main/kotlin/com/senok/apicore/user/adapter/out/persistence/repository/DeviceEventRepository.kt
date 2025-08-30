package com.senok.apicore.user.adapter.out.persistence.repository

import com.senok.apicore.user.adapter.out.persistence.entity.DeviceEventEntity
import org.springframework.data.repository.Repository

interface DeviceEventRepository: Repository<DeviceEventEntity, Long> {

    fun save(entity: DeviceEventEntity)
}