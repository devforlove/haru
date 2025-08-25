package com.senok.apicore.user.domain.user

import com.senok.corecommon.type.user.ProviderType

class Device(
    val id: Long? = null,
    val ownerId: Long,
    val deviceKey: String,
    val providerType: ProviderType
) {
    init {
        require(deviceKey.isNotBlank()) { "deviceKey는 비어있을 수 없습니다." }
    }

    companion object {
        fun register(
            ownerId: Long,
            deviceKey: String,
            providerType: ProviderType
        ): com.senok.apicore.user.domain.user.Device {
            return com.senok.apicore.user.domain.user.Device(
                id = null,
                ownerId = ownerId,
                deviceKey = deviceKey,
                providerType = providerType
            )
        }
    }
}