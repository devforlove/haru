package com.senok.user.adapter.out.persistence.entity

import com.senok.common.entity.BaseEntity
import jakarta.persistence.*

@Table(name = "device")
@Entity
class DeviceEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "device_key")
    val deviceKey: String,

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    val providerType: ProviderType,
): BaseEntity()