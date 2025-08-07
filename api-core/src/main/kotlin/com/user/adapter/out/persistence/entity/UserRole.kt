package com.senok.user.adapter.out.persistence.entity

import com.senok.common.db.entitty.EntityId
import jakarta.persistence.*

@Table(name = "user_role")
@Entity
class UserRole(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<UserRole>,

    @ManyToOne(fetch = FetchType.LAZY)
    val userId: User
)
