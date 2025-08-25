package com.senok.apicore.user.adapter.out.persistence.entity

import com.senok.corecommon.type.user.RoleType
import jakarta.persistence.*

@Table(name = "role")
@Entity
class RoleEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "role_type")
    val roleType: RoleType
)
