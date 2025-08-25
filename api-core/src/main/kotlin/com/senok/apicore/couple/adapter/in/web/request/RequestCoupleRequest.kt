package com.senok.apicore.couple.adapter.`in`.web.request

import com.senok.apicore.couple.application.`in`.command.RequestCoupleCommand

data class RequestCoupleRequest(
    val requestedUserId: Long
) {
    fun toCommand(userId: Long): RequestCoupleCommand {
        return RequestCoupleCommand(requestedUserId, userId)
    }
}