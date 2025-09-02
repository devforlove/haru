package com.senok.coreeventpublisher.publisher

import com.senok.coreeventpublisher.event.couple.CoupleRequestEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CoupleRequestKafkaProducer(
    private val coupleRequestEventKafkaTemplate: KafkaTemplate<String, Any>
) {
    
    fun produce(event: CoupleRequestEvent) {
        coupleRequestEventKafkaTemplate.send("couple.request.event", event)
    }
}