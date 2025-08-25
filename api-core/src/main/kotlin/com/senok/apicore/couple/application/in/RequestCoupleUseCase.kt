package com.senok.apicore.couple.application.`in`

import com.senok.apicore.couple.application.`in`.command.RequestCoupleCommand

interface RequestCoupleUseCase {
    fun requestCouple(command: RequestCoupleCommand)
}