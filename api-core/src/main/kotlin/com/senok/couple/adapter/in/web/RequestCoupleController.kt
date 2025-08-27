package com.senok.couple.adapter.`in`.web

import com.senok.coreweb.response.ResponseResult
import com.senok.couple.adapter.`in`.web.request.RequestCoupleRequest
import com.senok.couple.application.`in`.RequestCoupleUseCase
import com.senok.user.application.`in`.RegisterUserUseCase
import com.senok.user.domain.auth.PrincipalDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/couple/request"])
@RestController
class RequestCoupleController(
    val requestCoupleUseCase: RequestCoupleUseCase
) {

    @PostMapping
    fun register(
        @RequestBody request: RequestCoupleRequest,
        @AuthenticationPrincipal user: PrincipalDetails
    ): ResponseResult<Unit> {
        return request.toCommand(user.id).run {
            ResponseResult.Success(requestCoupleUseCase.requestCouple(this))
        }
    }
}