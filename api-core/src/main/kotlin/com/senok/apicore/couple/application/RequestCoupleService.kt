package com.senok.apicore.couple.application

import com.senok.apicore.common.transaction.Tx
import com.senok.apicore.couple.application.`in`.RequestCoupleUseCase
import com.senok.apicore.couple.application.`in`.command.RequestCoupleCommand
import com.senok.apicore.couple.application.out.FindCouplePort
import com.senok.apicore.couple.application.out.FindIndividualPort
import com.senok.apicore.couple.application.out.SaveCoupleCodePort
import com.senok.apicore.couple.application.out.SaveCouplePort
import com.senok.apicore.couple.domain.model.Couple
import com.senok.apicore.couple.domain.model.CoupleCode
import com.senok.apicore.couple.domain.model.Individual
import com.senok.apicore.couple.domain.service.ValidateRequestService
import com.senok.corecommon.type.user.GenderType
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class RequestCoupleService(
    private val findIndividualPort: FindIndividualPort,
    private val validateRequestService: ValidateRequestService,
    private val findCouplePort: FindCouplePort,
    private val saveCouplePort: SaveCouplePort,
    private val saveCoupleCodePort: SaveCoupleCodePort
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
            saveCoupleCodePort.saveCoupleCode(CoupleCode.generateCode(couple.coupleId))
        }
    }

    private fun toFemaleMalePair(a: Individual, b: Individual): Pair<Long, Long> =
        when {
            a.gender == GenderType.FEMALE && b.gender == GenderType.MALE -> a.userId to b.userId
            a.gender == GenderType.MALE && b.gender == GenderType.FEMALE -> b.userId to a.userId
            else -> throw ApiException(ErrorCode.CLIENT_ERROR, "Unsupported gender combination: ${a.gender} - ${b.gender}")
        }
}