package com.senok.apicore.user.application.out

import com.senok.apicore.user.domain.user.Device

interface RegisterDevicePort {
    fun registerDevice(device: Device)
}