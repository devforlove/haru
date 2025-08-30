package com.senok.apicore.couple.application.`in`.command

data class AcceptCoupleCommand(
    val userId: Long,
    val coupleRequestId: Long,
    val isAccepted: Boolean
)