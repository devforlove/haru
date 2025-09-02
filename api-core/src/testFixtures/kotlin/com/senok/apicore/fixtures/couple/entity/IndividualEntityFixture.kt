package com.senok.apicore.fixtures.couple.entity

import com.senok.apicore.couple.adapter.out.persistence.entity.IndividualEntity
import com.senok.corecommon.types.user.GenderType

class IndividualEntityFixture {

    companion object {
        fun getIndividualEntity(
            userId: Long = 1L,
            gender: GenderType = GenderType.MALE,
            isCouple: Boolean = false,
        ) = IndividualEntity(userId = userId, gender = gender, isCouple = isCouple)
    }
}