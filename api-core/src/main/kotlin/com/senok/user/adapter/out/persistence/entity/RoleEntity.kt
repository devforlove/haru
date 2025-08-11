package com.senok.user.adapter.out.persistence.entity

import jakarta.persistence.*

@Table(name = "role")
@Entity
class RoleEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "role_type")
    val roleType: RoleType
)
