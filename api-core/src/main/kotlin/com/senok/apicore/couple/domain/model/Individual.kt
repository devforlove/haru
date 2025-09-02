package com.senok.apicore.couple.domain.model

import com.senok.apicore.common.domain.DomainModel
import com.senok.corecommon.type.user.GenderType

class Individual(
    val userId: Long,
    val gender: GenderType,
    val isCouple: Boolean,
): DomainModel<Individual>() {

    companion object {
        fun initIndividual(userId: Long, gender: GenderType): Individual {
            return Individual(
                userId = userId,
                gender = gender,
                isCouple = false
            )
        }
    }
}