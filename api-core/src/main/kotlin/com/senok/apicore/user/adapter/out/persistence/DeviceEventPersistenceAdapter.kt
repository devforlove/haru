package com.senok.apicore.user.adapter.out.persistence

import com.senok.apicore.user.adapter.out.persistence.mapper.DeviceEventMapper
import com.senok.apicore.user.adapter.out.persistence.repository.DeviceEventRepository
import com.senok.apicore.user.application.out.SaveDeviceEventPort
import com.senok.coreeventpublisher.event.device.DeviceEvent
import org.springframework.stereotype.Component

@Component
class DeviceEventPersistenceAdapter(
    private val deviceEventRepository: DeviceEventRepository,
    private val mapper: DeviceEventMapper,
): SaveDeviceEventPort {

    override fun saveDeviceEvent(event: DeviceEvent) {
        mapper.toEntity(event).apply {
            deviceEventRepository.save(this)
        }
    }
}