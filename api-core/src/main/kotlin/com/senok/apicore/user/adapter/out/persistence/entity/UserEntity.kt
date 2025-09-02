package com.senok.apicore.user.adapter.out.persistence.entity

import com.senok.apicore.common.entity.BaseEntity
import com.senok.apicore.common.event.Events
import com.senok.corecommon.types.user.GenderType
import com.senok.coreeventpublisher.event.user.UserEventType
import com.senok.corecommon.types.user.UserStatus
import com.senok.coreeventpublisher.event.user.UserEvent
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "user")
@Entity
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long? = null,

    @Column(name = "email")
    val email: String = "inwook94@naver.com",

    @Column(name = "password")
    val password: String? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "profile_image")
    val profileImage: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    val gender: GenderType,

    @Column(name = "ruby_count")
    val rubyCount: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    val userStatus: UserStatus,
): BaseEntity() {

    companion object {

        fun initiateUser(email: String, nickname: String, profileImage: String): UserEntity {
            return UserEntity(
                email = email,
                nickname = nickname,
                profileImage = profileImage,
                userStatus = UserStatus.INITIATED,
                gender = GenderType.UNKNOWN,
            )
        }
    }

    @PostPersist
    private fun onPostCreate() {
        Events.raise(
            UserEvent(
                userId = id!!,
                eventType = UserEventType.REGISTER,
                attributes = UserEvent.UserEventAttribute(id, gender),
                createdAt = LocalDateTime.now()
            )
        )
    }
}
