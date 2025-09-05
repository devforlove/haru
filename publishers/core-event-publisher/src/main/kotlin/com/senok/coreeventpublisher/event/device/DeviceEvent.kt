package com.senok.coreeventpublisher.event.device

import com.senok.corecommon.types.user.ProviderType
import com.senok.coreeventpublisher.event.common.DomainEvent
import java.time.LocalDateTime

class DeviceEvent(
    val deviceId: Long,
    val eventType: DeviceEventType,
    val attributes: DeviceEventAttribute,
    val createdAt: LocalDateTime,
): DomainEvent<DeviceEventType, DeviceEvent.DeviceEventAttribute>(deviceId, eventType, attributes, createdAt) {

    data class DeviceEventAttribute(
        val userId: Long,
        val providerType: ProviderType?,
        val isNotiOn: Boolean
    )
}