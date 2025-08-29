package com.senok.apicore.couple.adapter.out.publisher

import com.senok.coreeventpublisher.event.couple.CoupleEvent
import com.senok.coreeventpublisher.publisher.CoupleEventKafkaProducer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CoupleCodeEventPublisher(
    private val coupleEventKafkaProducer: CoupleEventKafkaProducer,
) {

    @TransactionalEventListener
    fun handleInternalEvent(event: CoupleEvent) {
        coupleEventKafkaProducer.produce(event)
    }
}
