package com.senok.coreeventpublisher.publisher

import com.senok.coreeventpublisher.constants.TopicConstants
import com.senok.coreeventpublisher.event.user.UserEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class UserEventKafkaProducer(
    private val userEventKafkaTemplate: KafkaTemplate<String, Any>,
) {
    
    fun produce(event: UserEvent) {
        userEventKafkaTemplate.send(TopicConstants.DEVICE, event)
    }
}