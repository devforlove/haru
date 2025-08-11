package com.senok.user.adapter.out.persistence

import com.senok.user.application.out.RegisterDevicePort
import com.senok.user.domain.user.Device
import org.springframework.stereotype.Component

@Component
class DevicePersistenceAdapter: RegisterDevicePort {

    override fun registerDevice(device: Device) {
        TODO("Not yet implemented")
    }
}