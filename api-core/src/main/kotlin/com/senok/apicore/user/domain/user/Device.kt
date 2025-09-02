package com.senok.apicore.user.domain.user

import com.senok.apicore.common.domain.DomainModel
import com.senok.corecommon.type.user.ProviderType

class Device(
    val ownerId: Long,
    val deviceKey: String,
    val providerType: ProviderType,
    val isNotiOn: Boolean
): DomainModel<Device>() {
    
    init {
        require(deviceKey.isNotBlank()) { "deviceKey can not be blank." }
    }

    companion object {
        fun register(
            ownerId: Long,
            deviceKey: String,
            providerType: ProviderType
        ): Device {
            return Device(
                ownerId = ownerId,
                deviceKey = deviceKey,
                providerType = providerType,
                isNotiOn = true
            )
        }
    }
}