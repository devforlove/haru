package com.senok.apicore.fixtures.command

import com.senok.corecommon.type.user.GenderType
import com.senok.corecommon.type.user.ProviderType
import com.senok.user.application.`in`.command.RegisterUserCommand

class RegisterUserCommandFixture {

    companion object {
        fun getCommand(
            nickname: String = "hiho",
            genderType: GenderType = GenderType.MALE,
        ) =
            RegisterUserCommand(nickname, genderType, "01012345678", ProviderType.ANDROID)
    }
}