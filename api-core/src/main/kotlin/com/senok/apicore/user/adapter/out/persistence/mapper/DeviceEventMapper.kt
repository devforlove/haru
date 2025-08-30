package com.senok.apicore.user.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.user.adapter.out.persistence.entity.DeviceEventEntity
import com.senok.coreeventpublisher.event.device.DeviceEvent
import org.springframework.stereotype.Component

@Component
class DeviceEventMapper: CommonMapper<DeviceEventEntity, DeviceEvent> {

    override fun toEntity(domain: DeviceEvent): DeviceEventEntity {
        return DeviceEventEntity(
            domain.connId,
            domain.eventType,
            domain.attributes.toString()
        )
    }

    override fun toDomain(entity: DeviceEventEntity): DeviceEvent {
        throw UnsupportedOperationException("not supported function")
    }
}