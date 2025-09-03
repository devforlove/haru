package com.senok.apicore.user.adapter.out.persistence

import com.senok.apicore.user.adapter.out.persistence.mapper.UserMapper
import com.senok.apicore.user.adapter.out.persistence.repository.UserRepository
import com.senok.apicore.user.application.out.FindUserPort
import com.senok.apicore.user.application.out.SaveUserPort
import com.senok.apicore.user.domain.user.User
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val mapper: UserMapper,
): FindUserPort, SaveUserPort {

    override fun findUser(id: Long): User =
        userRepository.findById(id)
            ?.let(mapper::toDomain)
            ?: throw ApiException(ErrorCode.ENTITY_NOT_FOUND)
    
    override fun findUserByEmail(email: String): User? =
        userRepository.findByEmail(email)
            ?.let(mapper::toDomain)
    
    override fun saveUser(user: User): User {
        return mapper.toEntity(user)
            .let { mapper.toDomain(userRepository.save(it)) }
    }
}