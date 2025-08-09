package com.senok.user.adapter.out.persistence.entity

import com.senok.coredb.entity.BaseEntity
import com.senok.coredb.entity.EntityId
import jakarta.persistence.*

@Table(name = "device")
@Entity
class Device(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<Device> = EntityId(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "device_key")
    val deviceKey: String,

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    val providerType: ProviderType,
): BaseEntity()