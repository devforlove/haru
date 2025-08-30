package com.senok.apicore.couple.adapter.`in`.web

import com.senok.apicore.couple.adapter.`in`.web.request.AcceptCoupleRequest
import com.senok.apicore.couple.application.`in`.AcceptCoupleUseCase
import com.senok.apicore.user.domain.auth.PrincipalDetails
import com.senok.coreweb.response.ResponseResult
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/couple/accept"])
@RestController
class AcceptCoupleController(
    private val acceptCoupleUseCase: AcceptCoupleUseCase
) {

    @PostMapping
    fun accept(
        @RequestBody request: AcceptCoupleRequest,
        @AuthenticationPrincipal user: PrincipalDetails
    ): ResponseResult<Unit> {
        return request.toCommand(user.id).run {
            ResponseResult.Success(acceptCoupleUseCase.acceptCouple(this))
        }
    }
}