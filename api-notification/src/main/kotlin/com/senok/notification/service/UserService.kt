package com.senok.notification.service

import com.senok.coreeventpublisher.event.user.UserEvent
import com.senok.notification.dao.entity.User
import com.senok.notification.dao.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
) {
    
    fun saveUser(userEvent: UserEvent) {
        userRepository.save(
            User.create(
                userEvent.attributes.gender,
                userEvent.attributes.email,
                userEvent.attributes.nickname
            )
        )
    }
}