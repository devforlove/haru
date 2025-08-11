package com.senok.user.adapter.out.persistence.entity

import jakarta.persistence.*

@Table(name = "user_role")
@Entity
class UserRole(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val userId: UserEntity
)
