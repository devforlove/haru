package com.senok.notification.consumer

import com.senok.corecommon.types.couple.CoupleStatus
import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.couple.CoupleRequestEvent
import com.senok.coreeventpublisher.event.couple.CoupleRequestEventType
import com.senok.notification.service.CoupleService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CoupleRequestEventConsumer(
    private val coupleService: CoupleService,
) {
    
    @KafkaListener(
        topics = [TopicConstants.COUPLE_REQUEST],
        groupId = ConsumerGroupConstants.COUPLE_CONSUMER_GROUP,
        containerFactory = "coupleRequestEventConsumerFactory"
    )
    fun consume(consumerRecord: ConsumerRecord<String, CoupleRequestEvent>, ack: Acknowledgment) {
        consumerRecord.value()?.let {
            val coupleStatus = when (it.eventType) {
                CoupleRequestEventType.REQUESTING -> CoupleStatus.REQUESTING
                CoupleRequestEventType.ACCEPTED -> CoupleStatus.ACTIVE
                CoupleRequestEventType.REJECTED -> CoupleStatus.INACTIVE
            }
            
            coupleService.changeCouple(
                consumerRecord.value().attributes.coupleId,
                coupleStatus
            )
            
            ack.acknowledge()
        }
    }
}