package com.senok.user.adapter.out.persistence.entity

import com.senok.coredb.entity.EntityId
import jakarta.persistence.*

@Table(name = "role")
@Entity
class Role(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<Role>,

    @Column(name = "role_type")
    val roleType: RoleType
)
