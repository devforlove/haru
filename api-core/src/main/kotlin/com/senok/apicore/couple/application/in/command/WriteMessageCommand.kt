package com.senok.apicore.couple.application.`in`.command

data class WriteMessageCommand(
    val coupleId: Long,
    val coupleMessageId: Long,
    val coupleMessage: String,
    val userId: Long
)