package com.senok.apicore.couple.application.out

import com.senok.apicore.couple.domain.model.Couple

interface ChangeCouplePort {
    fun changeCoupleStatus(couple: Couple)
}