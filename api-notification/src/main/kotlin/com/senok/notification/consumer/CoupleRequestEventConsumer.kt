package com.senok.notification.consumer

import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.couple.CoupleRequestEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CoupleRequestEventConsumer {

    @KafkaListener(
        topics = [TopicConstants.COUPLE_REQUEST],
        groupId = ConsumerGroupConstants.COUPLE_CONSUMER_GROUP,
        containerFactory = "coupleRequestEventConsumerFactory"
    )
    fun consume(consumerRecords: List<ConsumerRecord<String, CoupleRequestEvent>>, ack: Acknowledgment) {
        
        ack.acknowledge()
    }
}