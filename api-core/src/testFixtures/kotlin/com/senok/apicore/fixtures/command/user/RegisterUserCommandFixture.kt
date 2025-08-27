package com.senok.apicore.fixtures.command.user

import com.senok.apicore.user.application.`in`.command.RegisterUserCommand
import com.senok.corecommon.type.user.GenderType
import com.senok.corecommon.type.user.ProviderType

class RegisterUserCommandFixture {

    companion object {
        fun getCommand(
            nickname: String = "hiho",
            genderType: GenderType = GenderType.MALE,
        ) =
            RegisterUserCommand(
                nickname,
                genderType,
                "01012345678",
                ProviderType.ANDROID
            )
    }
}