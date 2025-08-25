package com.senok.couple.application.out

import com.senok.couple.domain.model.Couple

interface FindCouplePort {
    fun findCouple(femaleId: Long, maleId: Long): Couple?
}