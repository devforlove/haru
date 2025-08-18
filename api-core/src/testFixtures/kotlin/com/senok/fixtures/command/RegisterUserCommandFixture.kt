package com.senok.fixtures.command

import com.senok.user.adapter.out.persistence.entity.GenderType
import com.senok.user.adapter.out.persistence.entity.ProviderType
import com.senok.user.application.`in`.command.RegisterUserCommand

class RegisterUserCommandFixture {

    companion object {
        fun getCommand() =
            RegisterUserCommand("nickname", GenderType.MALE, "phoneNumber", ProviderType.ANDROID)
    }
}