package com.senok.notification.consumer

import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CoupleMessageEventConsumer {

    @KafkaListener(
        topics = [TopicConstants.COUPLE_REQUEST],
        groupId = ConsumerGroupConstants.COUPLE_REQUEST_CONSUMER_GROUP,
        containerFactory = "coupleMessageEventConsumerFactory"
    )
    fun consume(consumerRecords: List<ConsumerRecord<String, CoupleMessageEvent>>, ack: Acknowledgment) {
        
        ack.acknowledge()
    }
}