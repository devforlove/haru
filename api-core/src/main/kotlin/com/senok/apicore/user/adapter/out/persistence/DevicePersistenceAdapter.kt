package com.senok.apicore.user.adapter.out.persistence

import com.senok.apicore.user.adapter.out.persistence.mapper.DeviceMapper
import com.senok.apicore.user.adapter.out.persistence.repository.DeviceRepository
import com.senok.apicore.user.application.out.RegisterDevicePort
import com.senok.apicore.user.domain.user.Device
import org.springframework.stereotype.Component

@Component
class DevicePersistenceAdapter(
    private val deviceRepository: DeviceRepository,
    private val deviceMapper: DeviceMapper
): RegisterDevicePort {

    override fun registerDevice(device: Device) {
        deviceRepository.save(deviceMapper.toEntity(device))
    }
}