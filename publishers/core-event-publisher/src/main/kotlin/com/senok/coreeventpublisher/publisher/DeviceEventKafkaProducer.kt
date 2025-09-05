package com.senok.coreeventpublisher.publisher

import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.device.DeviceEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class DeviceEventKafkaProducer(
    private val deviceEventKafkaTemplate: KafkaTemplate<String, Any>,
) {

    fun produce(event: DeviceEvent) {
        deviceEventKafkaTemplate.send(TopicConstants.DEVICE, event)
    }
}