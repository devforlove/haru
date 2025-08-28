package com.senok.apicore.user.adapter.out.persistence.mapper

import com.senok.apicore.common.entity.mapper.CommonMapper
import com.senok.apicore.user.adapter.out.persistence.entity.UserEventEntity
import com.senok.coreeventpublisher.event.user.UserEventType
import com.senok.coreeventpublisher.event.user.UserEvent
import org.springframework.stereotype.Component

@Component
class UserEventMapper: CommonMapper<UserEventEntity, UserEvent> {

    override fun toEntity(domain: UserEvent): UserEventEntity {
        return UserEventEntity(
            userId = domain.userId,
            eventType = UserEventType.REGISTER,
            attributes = domain.attributes.toString()
        )
    }

    override fun toDomain(entity: UserEventEntity): UserEvent {
        throw UnsupportedOperationException("not supported function")
    }
}