package com.senok.apicore.couple.adapter.out.publisher

import com.senok.coreeventpublisher.event.couple.CoupleMessageEvent
import com.senok.coreeventpublisher.publisher.CoupleMessageKafkaProducer
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoupleMessagePublisher(
    private val producer: CoupleMessageKafkaProducer
) {
    
    @TransactionalEventListener
    fun publish(event: CoupleMessageEvent) {
        producer.produce(event)
    }
}