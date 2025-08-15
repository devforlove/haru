package com.senok.user.adapter.out.persistence

import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import com.senok.user.adapter.out.persistence.mapper.UserMapper
import com.senok.user.adapter.out.persistence.repository.UserRepository
import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.FindUserPort
import com.senok.user.application.out.dto.RegisterInfoDto
import com.senok.user.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
): FindUserPort, UpdateUserPort {

    override fun findUser(id: Long): User =
        findByIdOrThrow(id)


    override fun updateRegisterInfo(dto: RegisterInfoDto) {
        val userEntity = findByIdOrThrow(dto.userId)
        userEntity.nickname = dto.nickname
        userEntity.gender = dto.genderType
    }

    private fun findByIdOrThrow(id: Long) =
        userRepository.findById(id)
            ?.let(userMapper::toDomain)
            ?: throw ApiException(ErrorCode.ENTITY_NOT_FOUND)
}