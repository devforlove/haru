package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.Couple

interface FindCouplePort {
    fun findCoupleByUserId(femaleId: Long, maleId: Long): Couple?
    fun findCoupleByCoupleId(coupleId: Long): Couple
}