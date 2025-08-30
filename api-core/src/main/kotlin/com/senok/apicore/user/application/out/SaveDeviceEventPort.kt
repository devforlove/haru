package com.senok.apicore.user.application.out

import com.senok.coreeventpublisher.event.device.DeviceEvent

interface SaveDeviceEventPort {
    fun saveDeviceEvent(event: DeviceEvent)
}