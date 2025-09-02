package com.senok.apicore.couple.adapter.out.publisher

import com.senok.coreeventpublisher.event.couple.CoupleRequestEvent
import com.senok.coreeventpublisher.publisher.CoupleRequestKafkaProducer
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoupleRequestPublisher(
    private val producer: CoupleRequestKafkaProducer
) {
    
    @TransactionalEventListener
    fun publish(event: CoupleRequestEvent) {
        producer.produce(event)
    }
}