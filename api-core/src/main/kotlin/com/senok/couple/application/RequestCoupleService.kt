package com.senok.couple.application

import com.senok.common.transaction.Tx
import com.senok.corecommon.type.user.GenderType
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import com.senok.couple.application.`in`.RequestCoupleUseCase
import com.senok.couple.application.`in`.command.RequestCoupleCommand
import com.senok.couple.application.out.FindCouplePort
import com.senok.couple.application.out.FindIndividualPort
import com.senok.couple.application.out.SaveCouplePort
import com.senok.couple.domain.model.Couple
import com.senok.couple.domain.model.Individual
import com.senok.couple.domain.service.ValidateRequestService
import org.springframework.stereotype.Service

@Service
class RequestCoupleService(
    private val findIndividualPort: FindIndividualPort,
    private val validateRequestService: ValidateRequestService,
    private val findCouplePort: FindCouplePort,
    private val saveCouplePort: SaveCouplePort,
): RequestCoupleUseCase {

    override fun requestCouple(command: RequestCoupleCommand) {
        Tx.writable {
            val requestUser = findIndividualPort.findIndividual(command.userId)
            val requestedUser = findIndividualPort.findIndividual(command.requestedUserId)
            validateRequestService.validateRequest(requestUser, requestedUser)

            val (femaleId, maleId) = toFemaleMalePair(requestUser, requestedUser)

            // 커플 생성
            val couple = findCouplePort.findCouple(femaleId, maleId)
                ?: saveCouplePort.saveCouple(Couple.initCouple(femaleId, maleId))

            // 커플 코드 생성
        }
    }

    private fun toFemaleMalePair(a: Individual, b: Individual): Pair<Long, Long> =
        when {
            a.gender == GenderType.FEMALE && b.gender == GenderType.MALE -> a.userId to b.userId
            a.gender == GenderType.MALE && b.gender == GenderType.FEMALE -> b.userId to a.userId
            else -> throw ApiException(ErrorCode.CLIENT_ERROR, "not supported gender combo: ${a.gender} - ${b.gender}")
        }
}