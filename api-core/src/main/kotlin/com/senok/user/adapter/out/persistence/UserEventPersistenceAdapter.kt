package com.senok.user.adapter.out.persistence

import com.senok.user.adapter.out.persistence.mapper.UserRegisterEventMapper
import com.senok.user.adapter.out.persistence.repository.UserEventRepository
import com.senok.user.application.out.SaveUserRegisterEventPort
import com.senok.user.domain.user.UserRegisterEvent
import org.springframework.stereotype.Component

@Component
class UserEventPersistenceAdapter(
    private val userEventRepository: UserEventRepository,
    private val mapper: UserRegisterEventMapper
): SaveUserRegisterEventPort {
    override fun saveUserRegisterEvent(event: UserRegisterEvent) {
        val entity = mapper.toEntity(event)
        userEventRepository.save(entity)
    }
}