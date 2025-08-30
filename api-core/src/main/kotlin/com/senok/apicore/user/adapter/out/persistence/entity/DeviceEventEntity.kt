package com.senok.apicore.user.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.coreeventpublisher.event.device.DeviceEventType
import jakarta.persistence.*

@Table(name = "device_event")
@Entity
class DeviceEventEntity(

    @Column(name = "device_id")
    val deviceId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    val eventType: DeviceEventType,

    @Column(name = "attributes")
    val attributes: String
): BaseEntity()