package com.senok.notification.dao.entity

import com.senok.corecommon.types.user.ProviderType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "notification_users")
class NotificationUsers(
    @Id
    val userId: Long? = null,
    val devices: List<Device>
) {
    data class Device(
        val deviceId: String,
        val isNotiOn: Boolean,
        val providerType: ProviderType,
    )
}