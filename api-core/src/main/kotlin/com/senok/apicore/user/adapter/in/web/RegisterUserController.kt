package com.senok.apicore.user.adapter.`in`.web

import com.senok.apicore.user.adapter.`in`.web.request.RegisterUserRequest
import com.senok.apicore.user.application.`in`.RegisterUserUseCase
import com.senok.apicore.user.domain.auth.PrincipalDetails
import com.senok.coreweb.response.ResponseResult
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/user"])
@RestController
class RegisterUserController(
    val useCase: RegisterUserUseCase
) {

    @PostMapping
    fun register(
        @RequestBody request: RegisterUserRequest,
        @AuthenticationPrincipal user: PrincipalDetails
    ): ResponseResult<Unit> {
        return request.toCommand().run {
            ResponseResult.Success(useCase.registerUser(this, user.id))
        }
    }
}