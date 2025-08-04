package com.senok.user.adapter.out.persistence.entity

import com.senok.common.db.entitty.EntityId
import jakarta.persistence.*

@Table(name = "role")
@Entity
class Role(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: EntityId<Role>,

    @Column(name = "name")
    val name: String
)
