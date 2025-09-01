package com.senok.apicore.couple.application

import com.senok.apicore.common.transaction.Tx
import com.senok.apicore.couple.application.`in`.WriteMessageUseCase
import com.senok.apicore.couple.application.`in`.command.WriteMessageCommand
import com.senok.apicore.couple.application.out.FindCoupleMessagePort
import com.senok.apicore.couple.application.out.FindIndividualPort
import com.senok.apicore.couple.application.out.WriteCoupleMessagePort
import org.springframework.stereotype.Service

@Service
class WriteMessageService(
    private val findCoupleMessagePort: FindCoupleMessagePort,
    private val findIndividualPort: FindIndividualPort,
    private val writeCoupleMessagePort: WriteCoupleMessagePort
) : WriteMessageUseCase {

    override fun writeMessage(command: WriteMessageCommand) {
        val coupleMessage = findCoupleMessagePort.findCoupleMessage(command.coupleMessageId)
        val individual = findIndividualPort.findIndividual(command.userId)

        coupleMessage.writeMessage(individual.gender, command.coupleMessage)
        
        Tx.writable {
            writeCoupleMessagePort.writeMessage(coupleMessage)
        }
    }
}