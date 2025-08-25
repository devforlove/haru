package com.senok.apicore.couple.adapter.`in`.web

import com.senok.apicore.couple.adapter.`in`.web.request.RequestCoupleRequest
import com.senok.apicore.couple.application.`in`.RequestCoupleUseCase
import com.senok.apicore.user.domain.auth.PrincipalDetails
import com.senok.coreweb.response.ResponseResult
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