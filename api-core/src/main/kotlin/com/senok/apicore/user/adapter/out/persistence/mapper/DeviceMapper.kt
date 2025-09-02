package com.senok.apicore.user.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.user.adapter.out.persistence.entity.DeviceEntity
import com.senok.apicore.user.domain.user.Device
import org.springframework.stereotype.Component

@Component
class DeviceMapper: CommonMapper<DeviceEntity, Device> {

    override fun toEntity(domain: Device): DeviceEntity {
        return DeviceEntity(
            userId = domain.ownerId,
            deviceKey = domain.deviceKey,
            providerType = domain.providerType,
            isNotiOn = domain.isNotiOn,
        )
    }

    override fun toDomain(entity: DeviceEntity): Device {
        return Device(
            ownerId = entity.userId,
            deviceKey = entity.deviceKey,
            providerType = entity.providerType,
            isNotiOn = entity.isNotiOn,
        ).assignId(entity.id!!)
    }
}