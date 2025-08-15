package com.senok.user.adapter.out.persistence.mapper

import com.senok.common.mapper.CommonMapper
import com.senok.user.adapter.out.persistence.entity.UserEntity
import com.senok.user.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserMapper: CommonMapper<UserEntity, User> {

    override fun toEntity(domain: User): UserEntity {
        throw UnsupportedOperationException()
    }

    override fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id!!,
            email = entity.email,
            name = entity.name?:"",
            nickname = entity.nickname,
            profileImage = entity.profileImage?:"",
            gender = entity.gender,
            rubyCount = entity.rubyCount,
            userStatus =  entity.userStatus
        )
    }
}