package com.senok.apicore.couple.application.`in`

import com.senok.apicore.couple.application.`in`.command.AcceptCoupleCommand

interface AcceptCoupleUseCase {
    fun acceptCouple(command: AcceptCoupleCommand)
}