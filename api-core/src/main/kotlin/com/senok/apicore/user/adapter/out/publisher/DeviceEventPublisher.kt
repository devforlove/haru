package com.senok.apicore.user.adapter.out.publisher

import com.senok.coreeventpublisher.event.device.DeviceEvent
import com.senok.coreeventpublisher.publisher.DeviceEventKafkaProducer
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class DeviceEventPublisher(
    private val producer: DeviceEventKafkaProducer,
) {

    @TransactionalEventListener
    fun publish(event: DeviceEvent) {
        producer.produce(event)
    }
}