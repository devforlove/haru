package com.senok.notification.consumer

import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CoupleEventConsumer {

    @KafkaListener(
        topics = [TopicConstants.COUPLE_EVENT],
        groupId = ConsumerGroupConstants.COUPLE_EVENT_CONSUMER_GROUP,
        containerFactory = "coupleEventContainerFactory"
    )
    fun consume(consumerRecords: List<ConsumerRecord<String, CoupleEvent>>, ack: Acknowledgment) {
        
        ack.acknowledge()
    }

}