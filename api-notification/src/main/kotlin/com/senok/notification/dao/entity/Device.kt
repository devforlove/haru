package com.senok.notification.dao.entity

import com.senok.corecommon.types.user.ProviderType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class Device(
    @Column(name = "user_id")
    val userId: Long,
    
    @Column(name = "device_key")
    val deviceKey: String,
    
    @Column(name = "is_on")
    val isOn: Boolean,
    
    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type")
    val providerType: ProviderType,
): BaseEntity() {
    
    companion object {
        fun create(
            userId: Long,
            deviceKey: String,
            providerType: ProviderType,
        ): Device {
            return Device(
                userId,
                deviceKey,
                true,
                providerType
            )
        }
    }
}