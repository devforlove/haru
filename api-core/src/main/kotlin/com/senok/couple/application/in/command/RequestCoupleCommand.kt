package com.senok.couple.application.`in`.command

data class RequestCoupleCommand(
    val requestedUserId: Long,
    val userId: Long
)