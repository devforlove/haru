package com.senok.notification.dao.entity

import com.senok.corecommon.types.user.GenderType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "users")
@Entity
class User(
    @Column(name = "gender")
    val gender: GenderType,
    
    @Column(name = "email")
    val email: String,
    
    @Column(name = "nickname")
    val nickname: String,
): BaseEntity() {
    companion object {
        fun create(
            gender: GenderType,
            email: String,
            nickname: String,
        ): User {
            return User(
                gender, email, nickname
            )
        }
    }
}