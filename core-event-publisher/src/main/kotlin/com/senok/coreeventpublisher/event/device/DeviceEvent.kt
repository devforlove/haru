package com.senok.coreeventpublisher.event.device

import com.senok.corecommon.type.user.ProviderType
import com.senok.coreeventpublisher.common.DomainEvent
import java.time.LocalDateTime

class DeviceEvent(
    val deviceId: Long,
    override val eventType: DeviceEventType,
    override val attributes: DeviceEventAttribute,
    override val createdAt: LocalDateTime,
): DomainEvent<DeviceEventType, DeviceEvent.DeviceEventAttribute>(deviceId, eventType, attributes, createdAt) {

    data class DeviceEventAttribute(
        val userId: Long,
        val providerType: ProviderType,
        val isNotiOn: Boolean
    )
}