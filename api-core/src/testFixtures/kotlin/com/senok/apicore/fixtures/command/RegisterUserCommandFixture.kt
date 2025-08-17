package com.senok.apicore.fixtures.command

import com.senok.user.adapter.out.persistence.entity.GenderType
import com.senok.user.adapter.out.persistence.entity.ProviderType
import com.senok.user.application.`in`.command.RegisterUserCommand


class RegisterUserCommandFixture {

    companion object {
        fun getCommand() =
            RegisterUserCommand("hiho", GenderType.MALE, "d-392xi", "01012345678", ProviderType.ANDROID)
    }
}