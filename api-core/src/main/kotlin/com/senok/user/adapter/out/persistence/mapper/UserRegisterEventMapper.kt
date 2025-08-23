package com.senok.user.adapter.out.persistence.mapper

import com.senok.common.entity.mapper.CommonMapper
import com.senok.user.adapter.out.persistence.entity.UserEventEntity
import com.senok.user.adapter.out.persistence.entity.UserEventType
import com.senok.user.domain.user.UserRegisterEvent
import org.springframework.stereotype.Component

@Component
class UserRegisterEventMapper: CommonMapper<UserEventEntity, UserRegisterEvent> {

    override fun toEntity(domain: UserRegisterEvent): UserEventEntity {
        return UserEventEntity(
            userId = domain.userId,
            eventType = UserEventType.REGISTER,
            attributes = domain.attributes
        )
    }

    override fun toDomain(entity: UserEventEntity): UserRegisterEvent {
        throw UnsupportedOperationException("not supported function")
    }
}