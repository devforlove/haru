package com.senok.apicore.couple.adapter.`in`.web.request

import com.senok.apicore.couple.application.`in`.command.AcceptCoupleCommand

data class AcceptCoupleRequest(
    val coupleRequestId: Long,
    val isAccepted: Boolean
) {
    fun toCommand(userId: Long): AcceptCoupleCommand {
        return AcceptCoupleCommand(userId, coupleRequestId, isAccepted)
    }
}