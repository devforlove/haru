package com.senok.apicore.fixtures.command.couple

import com.senok.apicore.couple.application.`in`.command.AcceptCoupleCommand

class AcceptCoupleCommandFixture {

    companion object {
        fun getCommand(
            userId: Long,
            coupleRequestId: Long,
            isAccepted: Boolean
        ) =
            AcceptCoupleCommand(
                userId = userId,
                coupleRequestId = coupleRequestId,
                isAccepted = isAccepted
            )
    }
}