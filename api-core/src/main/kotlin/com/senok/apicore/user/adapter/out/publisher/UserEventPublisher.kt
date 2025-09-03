package com.senok.apicore.user.adapter.out.publisher

import com.senok.coreeventpublisher.event.user.UserEvent
import com.senok.coreeventpublisher.publisher.UserEventKafkaProducer
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserEventPublisher(
    private val producer: UserEventKafkaProducer,
) {
    
    @TransactionalEventListener
    fun publish(event: UserEvent) {
        producer.produce(event)
    }
}