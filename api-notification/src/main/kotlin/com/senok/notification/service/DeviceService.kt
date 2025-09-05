package com.senok.notification.service

import com.senok.coreeventpublisher.event.device.DeviceEvent
import com.senok.notification.dao.entity.Device
import com.senok.notification.dao.repository.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(
    val deviceRepository: DeviceRepository,
) {
    
    fun saveDevice(deviceEvent: DeviceEvent) {
        deviceRepository.save(Device.create(
            deviceEvent.attributes.userId,
            deviceEvent.attributes.deviceKey,
            deviceEvent.attributes.providerType!!
        ))
    }
}