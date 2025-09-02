package com.senok.apicore.user.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.apicore.common.event.Events
import com.senok.corecommon.types.user.ProviderType
import com.senok.coreeventpublisher.event.device.DeviceEvent
import com.senok.coreeventpublisher.event.device.DeviceEventType
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "device")
@Entity
class DeviceEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "device_key")
    val deviceKey: String,

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    val providerType: ProviderType,

    @Column(name = "is_noti_on")
    val isNotiOn: Boolean,
): BaseEntity() {

    @PostPersist
    private fun onPostCreate() {
        Events.raise(
            DeviceEvent(
                id!!,
                DeviceEventType.REGISTER,
                DeviceEvent.DeviceEventAttribute(
                    userId = userId,
                    providerType = providerType,
                    isNotiOn = isNotiOn,
                ),
                LocalDateTime.now()
            )
        )
    }
}