package com.senok.user.application.out

import com.senok.user.domain.user.Device

interface RegisterDevicePort {
    fun registerDevice(device: Device)
}