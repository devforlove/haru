package com.senok.apicore.couple.application.`in`.command

data class RequestCoupleCommand(
    val requestedUserId: Long,
    val userId: Long
)