package com.senok.notification.consumer

import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.device.DeviceEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class DeviceEventConsumer {
    
    @KafkaListener(
        topics = [TopicConstants.DEVICE],
        groupId = ConsumerGroupConstants.DEVICE_CONSUMER_GROUP,
        containerFactory = "coupleEventContainerFactory"
    )
    fun consume(consumerRecords: List<ConsumerRecord<String, DeviceEvent>>, ack: Acknowledgment) {
        
        ack.acknowledge()
    }
}