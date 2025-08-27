package com.senok.couple.application

import com.senok.couple.application.`in`.RequestCoupleUseCase
import com.senok.couple.application.`in`.command.RequestCoupleCommand
import com.senok.couple.application.out.FindIndividualPort
import org.springframework.stereotype.Service

@Service
class RequestCoupleService(
    private val findIndividualPort: FindIndividualPort
): RequestCoupleUseCase {

    override fun requestCouple(command: RequestCoupleCommand) {
        val requestedIndividual = findIndividualPort.findIndividual(command.requestedUserId)

        // 커플 생성

        // 코드 생성

        // noti 발행
    }
}