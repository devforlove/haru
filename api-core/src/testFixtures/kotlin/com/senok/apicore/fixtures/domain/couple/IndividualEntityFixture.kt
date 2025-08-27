package com.senok.apicore.fixtures.domain.couple

import com.senok.apicore.couple.adapter.out.persistence.entity.IndividualEntity
import com.senok.corecommon.type.user.GenderType

class IndividualEntityFixture {

    companion object {
        fun getUserEntity(
            userId: Long = 1L,
            gender: GenderType = GenderType.MALE,
            isCouple: Boolean = false,
        ) = IndividualEntity(userId = userId, gender = gender, isCouple = isCouple)
    }
}