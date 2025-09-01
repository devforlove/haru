package com.senok.apicore.couple.adapter.`in`.web

import com.senok.apicore.couple.adapter.`in`.web.request.WriteMessageRequest
import com.senok.apicore.couple.application.`in`.WriteMessageUseCase
import com.senok.apicore.user.domain.auth.PrincipalDetails
import com.senok.coreweb.response.ResponseResult
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/couple/message"])
@RestController
class WriteMessageController(
    private val useCase: WriteMessageUseCase
) {

    @PostMapping
    fun writeMessage(
        @RequestBody request: WriteMessageRequest,
        @AuthenticationPrincipal user: PrincipalDetails
    ): ResponseResult<Unit> {
        return request.toCommand(user.id).run {
            ResponseResult.Success(useCase.writeMessage(this))
        }
    }
}