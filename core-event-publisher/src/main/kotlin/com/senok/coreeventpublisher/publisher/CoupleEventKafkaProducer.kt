package com.senok.coreeventpublisher.publisher

import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CoupleEventKafkaProducer(
    private val coupleEventKafkaTemplate: KafkaTemplate<String, Any>,
) {

    fun produce(event: CoupleEvent) {
        coupleEventKafkaTemplate.send("couple.event", event)
    }
}