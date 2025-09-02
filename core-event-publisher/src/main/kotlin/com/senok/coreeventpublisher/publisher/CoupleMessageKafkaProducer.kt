package com.senok.coreeventpublisher.publisher

import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CoupleMessageKafkaProducer(
    private val coupleMessageEventKafkaTemplate: KafkaTemplate<String, Any>,
) {
    
    fun produce(event: CoupleMessageEvent) {
        coupleMessageEventKafkaTemplate.send(TopicConstants.COUPLE_MESSAGE, event)
    }
}