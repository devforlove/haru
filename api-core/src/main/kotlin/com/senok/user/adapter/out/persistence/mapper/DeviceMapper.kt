package com.senok.user.adapter.out.persistence.mapper

import com.senok.common.entity.mapper.CommonMapper
import com.senok.user.adapter.out.persistence.entity.DeviceEntity
import com.senok.user.domain.user.Device
import org.springframework.stereotype.Component

@Component
class DeviceMapper: CommonMapper<DeviceEntity, Device> {

    override fun toEntity(domain: Device): DeviceEntity {
        return DeviceEntity(
            userId = domain.ownerId,
            deviceKey = domain.deviceKey,
            providerType = domain.providerType
        )
    }

    override fun toDomain(entity: DeviceEntity): Device {
        return Device(
            id = entity.id,
            ownerId = entity.userId,
            deviceKey = entity.deviceKey,
            providerType = entity.providerType
        )
    }
}