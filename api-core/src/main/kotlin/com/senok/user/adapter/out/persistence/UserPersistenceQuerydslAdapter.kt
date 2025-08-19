package com.senok.user.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import com.senok.user.adapter.out.persistence.entity.QUserEntity
import com.senok.user.application.out.UpdateUserPort
import com.senok.user.application.out.dto.RegisterInfoDto
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserPersistenceQuerydslAdapter(
    private val queryFactory: JPAQueryFactory,
): UpdateUserPort {

    @Transactional
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