package com.senok.user.adapter.out.persistence.entity

import jakarta.persistence.*

@Table(name = "role")
@Entity
class Role(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "name")
    val name: String
)
