package com.senok.notification.consumer

import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CoupleEventConsumer {

    @KafkaListener(
        topics = ["couple.event"],
        groupId = "couple-event-consumer-group-id",
        containerFactory = "coupleEventContainerFactory"
    )
    fun consume(consumerRecords: List<ConsumerRecord<String, CoupleEvent>>, ack: Acknowledgment) {


        ack.acknowledge()
    }

}