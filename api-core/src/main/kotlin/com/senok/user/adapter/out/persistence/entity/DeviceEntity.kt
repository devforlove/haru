package com.senok.user.adapter.out.persistence.entity

import com.senok.coredb.entity.BaseEntity
import jakarta.persistence.*

@Table(name = "device")
@Entity
class DeviceEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column(name = "device_key")
    val deviceKey: String,

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    val providerType: ProviderType,
): BaseEntity()