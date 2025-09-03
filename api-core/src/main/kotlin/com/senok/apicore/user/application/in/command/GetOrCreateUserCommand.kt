package com.senok.apicore.user.application.`in`.command

data class GetOrCreateUserCommand(
    val email: String,
    val nickname: String,
    val profile: String
)