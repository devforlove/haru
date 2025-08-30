package com.senok.apicore.couple.application

import com.senok.apicore.common.transaction.Tx
import com.senok.apicore.couple.application.`in`.AcceptCoupleUseCase
import com.senok.apicore.couple.application.`in`.command.AcceptCoupleCommand
import com.senok.apicore.couple.application.out.ChangeCouplePort
import com.senok.apicore.couple.application.out.ChangeCoupleRequestPort
import com.senok.apicore.couple.application.out.FindCouplePort
import com.senok.apicore.couple.application.out.FindCoupleRequestPort
import com.senok.corecommon.type.couple.CoupleStatus
import com.senok.coreweb.exception.ApiException
import com.senok.coreweb.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class AcceptCoupleService(
    private val findCoupleRequestPort: FindCoupleRequestPort,
    private val findCouplePort: FindCouplePort,
    private val changeCoupleRequestPort: ChangeCoupleRequestPort,
    private val changeCouplePort: ChangeCouplePort,
): AcceptCoupleUseCase {

    override fun acceptCouple(command: AcceptCoupleCommand) {
        Tx.writable {
            val coupleRequest = findCoupleRequestPort.findCoupleRequest(command.coupleRequestId)
            val couple = findCouplePort.findCoupleByCoupleId(coupleRequest.coupleId)

            if(!coupleRequest.isRequesting)
                throw ApiException(ErrorCode.CLIENT_ERROR, "Accept couple is not valid, couple request is not requesting.")
            else {
                coupleRequest.changeTypeOnRequest(command.isAccepted)
                couple.changeStatus(CoupleStatus.ACTIVE)
            }

            changeCoupleRequestPort.changeCoupleRequest(coupleRequest)
            changeCouplePort.changeCoupleStatus(couple)
        }
    }
}