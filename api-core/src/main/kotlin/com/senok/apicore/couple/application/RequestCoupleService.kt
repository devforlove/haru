package com.senok.apicore.couple.application

import com.senok.apicore.common.transaction.Tx
import com.senok.apicore.couple.application.`in`.RequestCoupleUseCase
import com.senok.apicore.couple.application.`in`.command.RequestCoupleCommand
import com.senok.apicore.couple.application.out.*
import com.senok.apicore.couple.domain.model.Couple
import com.senok.apicore.couple.domain.model.CoupleCode
import com.senok.apicore.couple.domain.model.CoupleRequest
import com.senok.apicore.couple.domain.model.Individual
import com.senok.apicore.couple.domain.service.ValidateRequestService
import com.senok.corecommon.types.user.GenderType
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class RequestCoupleService(
    private val findIndividualPort: FindIndividualPort,
    private val validateRequestService: ValidateRequestService,
    private val findCouplePort: FindCouplePort,
    private val saveCouplePort: SaveCouplePort,
    private val saveCoupleRequestPort: SaveCoupleRequestPort,
    private val saveCoupleCodePort: SaveCoupleCodePort
) : RequestCoupleUseCase {

    override fun requestCouple(command: RequestCoupleCommand) {
        val requestUser = findIndividualPort.findIndividual(command.userId)
        val requestedUser = findIndividualPort.findIndividual(command.requestedUserId)
        validateRequestService.validateRequest(requestUser, requestedUser)

        val (femaleId, maleId) = toFemaleMalePair(requestUser, requestedUser)

        Tx.writable {
            // 커플 생성
            val couple = findCouplePort.findCoupleByUserId(femaleId, maleId)
                ?: saveCouplePort.saveCouple(Couple.initCouple(femaleId, maleId))

            // 커플 요청 생성
            saveCoupleRequestPort.saveCoupleRequest(CoupleRequest.initRequest(couple.id))

            // 커플 코드 생성 및 이벤트 발행
            saveCoupleCodePort.saveCoupleCode(CoupleCode.generateCode(couple.id))
        }
    }

    private fun toFemaleMalePair(a: Individual, b: Individual): Pair<Long, Long> =
        when {
            a.gender == GenderType.FEMALE && b.gender == GenderType.MALE -> a.userId to b.userId
            a.gender == GenderType.MALE && b.gender == GenderType.FEMALE -> b.userId to a.userId
            else -> throw ApiException(
                ErrorCode.CLIENT_ERROR,
                "Unsupported gender combination: ${a.gender} - ${b.gender}"
            )
        }
}