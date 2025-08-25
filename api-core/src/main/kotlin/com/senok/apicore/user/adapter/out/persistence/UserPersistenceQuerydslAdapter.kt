package com.senok.apicore.user.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import com.senok.apicore.user.adapter.out.persistence.entity.QUserEntity
import com.senok.apicore.user.application.out.UpdateUserPort
import com.senok.apicore.user.application.out.dto.RegisterInfoDto
import org.springframework.stereotype.Component

@Component
class UserPersistenceQuerydslAdapter(
    private val queryFactory: JPAQueryFactory,
): UpdateUserPort {

    override fun updateRegisterInfo(dto: RegisterInfoDto) {
        val qUser = QUserEntity.userEntity
        queryFactory
            .update(qUser)
            .set(qUser.nickname, dto.nickname)
            .set(qUser.gender, dto.genderType)
            .where(qUser.id.eq(dto.userId))
            .execute()
    }
}