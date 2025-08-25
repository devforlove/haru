package com.senok.user.adapter.out.persistence

import com.senok.user.adapter.out.persistence.mapper.UserRegisterEventMapper
import com.senok.user.adapter.out.persistence.repository.UserEventRepository
import com.senok.user.application.out.SaveUserRegisterEventPort
import com.senok.coreeventpublisher.user.UserEvent
import org.springframework.stereotype.Component

@Component
class UserEventPersistenceAdapter(
    private val userEventRepository: UserEventRepository,
    private val mapper: UserRegisterEventMapper
): SaveUserRegisterEventPort {
    override fun saveUserRegisterEvent(event: UserEvent) {
        val entity = mapper.toEntity(event)
        userEventRepository.save(entity)
    }
}