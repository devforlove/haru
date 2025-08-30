package com.senok.apicore.couple.adapter.out.publisher

import com.senok.coreeventpublisher.event.couple.CoupleEvent
import com.senok.coreeventpublisher.publisher.CoupleEventKafkaProducer
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoupleEventPublisher(
    private val producer: CoupleEventKafkaProducer,
) {

    @TransactionalEventListener
    fun publish(event: CoupleEvent) {
        producer.produce(event)
    }
}
