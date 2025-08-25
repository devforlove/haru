package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.Couple

interface SaveCouplePort {
    fun saveCouple(couple: Couple): Couple
}