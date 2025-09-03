package com.senok.apicore.fixtures.user.command

import com.senok.apicore.user.application.`in`.command.GetOrCreateUserCommand
import com.senok.apicore.user.application.`in`.command.RegisterUserCommand
import com.senok.corecommon.types.user.GenderType
import com.senok.corecommon.types.user.ProviderType

class GetOrCreateUserCommandFixture {

    companion object {
        fun getCommand(
            email: String = "user@example.com",
            nickname: String = "hiho",
            profile: String = "example.com/img.png",
        ) =
            GetOrCreateUserCommand(
                email,
                nickname,
                profile
            )
    }
}