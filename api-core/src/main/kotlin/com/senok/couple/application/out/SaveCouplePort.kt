package com.senok.couple.application.out

import com.senok.couple.domain.model.Couple

interface SaveCouplePort {
    fun saveCouple(couple: Couple): Couple
}