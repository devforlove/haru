package com.senok.apicore.fixtures.couple.command

import com.senok.apicore.couple.application.`in`.command.RequestCoupleCommand

class RequestCoupleCommandFixture {

    companion object {
        fun getCommand(
            requestedUserId: Long,
            userId: Long,
        ) =
            RequestCoupleCommand(
                requestedUserId,
                userId
            )
    }
}