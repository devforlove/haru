package com.senok.apicore.user.adapter.out.persistence

import com.senok.apicore.user.adapter.out.persistence.mapper.UserEventMapper
import com.senok.apicore.user.adapter.out.persistence.repository.UserEventRepository
import com.senok.apicore.user.application.out.SaveUserEventPort
import com.senok.coreeventpublisher.event.user.UserEvent
import org.springframework.stereotype.Component

@Component
class UserEventPersistenceAdapter(
    private val userEventRepository: UserEventRepository,
    private val mapper: UserEventMapper
): SaveUserEventPort {
    override fun saveUserEvent(event: UserEvent) {
        val entity = mapper.toEntity(event)
        userEventRepository.save(entity)
    }
}