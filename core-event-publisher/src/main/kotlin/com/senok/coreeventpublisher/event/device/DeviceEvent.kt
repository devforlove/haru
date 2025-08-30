package com.senok.coreeventpublisher.event.device

import com.senok.corecommon.type.user.ProviderType
import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class DeviceEvent(
    val deviceId: Long,
    val _eventType: DeviceEventType,
    val _attributes: DeviceEventAttribute,
    val _createdAt: LocalDateTime,
): DomainEvent<DeviceEventType, DeviceEvent.DeviceEventAttribute>(deviceId, _eventType, _attributes, _createdAt) {

    data class DeviceEventAttribute(
        val userId: Long,
        val providerType: ProviderType,
        val isNotiOn: Boolean
    )
}