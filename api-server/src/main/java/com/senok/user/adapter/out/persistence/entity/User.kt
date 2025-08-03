package com.senok.user.adapter.out.persistence.entity

import com.senok.common.db.entitty.BaseEntity
import jakarta.persistence.*

@Table(name = "user")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "login_id")
    val loginId: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "name", nullable = true)
    val name: String?,

    @Column(name = "name")
    val nickname: String,

    @Column(name = "gender")
    val gender: String,

    @Column(name = "ruby_count")
    val rubyCount: Int = 0,

    @Enumerated(EnumType.STRING)
    val status: String,
): BaseEntity()
