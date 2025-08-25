package com.senok.couple.application.`in`

import com.senok.couple.application.`in`.command.RequestCoupleCommand

interface RequestCoupleUseCase {
    fun requestCouple(command: RequestCoupleCommand)
}