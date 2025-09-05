package com.senok.notification.consumer

import com.senok.coreeventpublisher.constants.ConsumerGroupConstants
import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.device.DeviceEvent
import com.senok.coreeventpublisher.event.device.DeviceEventType
import com.senok.coreeventpublisher.event.user.UserEventType
import com.senok.notification.dao.repository.DeviceRepository
import com.senok.notification.service.DeviceService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class DeviceEventConsumer(
    private val deviceService: DeviceService,
) {
    
    @KafkaListener(
        topics = [TopicConstants.DEVICE],
        groupId = ConsumerGroupConstants.DEVICE_CONSUMER_GROUP,
        containerFactory = "coupleEventContainerFactory"
    )
    fun consume(consumerRecord: ConsumerRecord<String, DeviceEvent>, ack: Acknowledgment) {
        consumerRecord.value()?.let {
            when (it.eventType) {
                DeviceEventType.REGISTER -> deviceService.saveDevice(consumerRecord.value())
            }
        }
        
        ack.acknowledge()
    }
}