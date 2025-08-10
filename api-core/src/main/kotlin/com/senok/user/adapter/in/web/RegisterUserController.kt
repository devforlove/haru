package com.senok.user.adapter.`in`.web

import com.senok.coreweb.response.ResponseResult
import com.senok.user.adapter.`in`.web.request.RegisterUserRequest
import com.senok.user.application.`in`.RegisterUserUseCase
import com.senok.user.domain.auth.PrincipalDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/user")
class RegisterUserController(
    val registerUserUseCase: RegisterUserUseCase
) {

    @PostMapping
    fun register(
        @RequestBody request: RegisterUserRequest,
        @AuthenticationPrincipal user: PrincipalDetails
    ): ResponseResult<Unit> {
        return request.toCommand().run {
            ResponseResult.Success(registerUserUseCase.registerUser(this, user.id))
        }
    }
}