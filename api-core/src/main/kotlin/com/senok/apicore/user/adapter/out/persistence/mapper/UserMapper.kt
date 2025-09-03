package com.senok.apicore.user.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.user.adapter.out.persistence.entity.UserEntity
import com.senok.apicore.user.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserMapper: CommonMapper<UserEntity, User> {

    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            email = domain.email,
            nickname = domain.nickname,
            profileImage = domain.profileImage,
            gender = domain.gender,
            rubyCount = domain.rubyCount,
            userStatus = domain.userStatus
        )
    }

    override fun toDomain(entity: UserEntity): User {
        return User(
            email = entity.email,
            nickname = entity.nickname,
            profileImage = entity.profileImage?:"",
            gender = entity.gender,
            rubyCount = entity.rubyCount,
            userStatus =  entity.userStatus
        ).assignId(entity.id!!)
    }
}