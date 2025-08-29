package com.senok.coreeventpublisher.publisher

import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CoupleEventKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {

    fun produce(event: CoupleEvent) {
        kafkaTemplate.send("couple.event", event)
    }
}