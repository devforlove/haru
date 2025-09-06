package com.senok.notification.consumer

import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import com.senok.coreeventpublisher.event.couple.CoupleEventType
import com.senok.coreeventpublisher.event.device.DeviceEventType
import com.senok.notification.service.CoupleService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CoupleEventConsumer(
    private val coupleService: CoupleService,
) {

    @KafkaListener(
        topics = [TopicConstants.COUPLE],
        groupId = ConsumerGroupConstants.COUPLE_CONSUMER_GROUP,
        containerFactory = "coupleEventContainerFactory"
    )
    fun consume(consumerRecord: ConsumerRecord<String, CoupleEvent>, ack: Acknowledgment) {
        consumerRecord.value()?.let {
            when (it.eventType) {
                CoupleEventType.CREATE -> coupleService.saveCouple(consumerRecord.value())
            }
        }
        
        ack.acknowledge()
    }
}