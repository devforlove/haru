package com.senok.couple.domain.model

import com.senok.corecommon.type.user.GenderType

class Individual(
    val userId: Long,
    val gender: GenderType,
    val isCouple: Boolean,
) {

    companion object {

        fun initIndividual(userId: Long, gender: GenderType): Individual {
            return Individual(userId, gender, isCouple = false)
        }
    }
}