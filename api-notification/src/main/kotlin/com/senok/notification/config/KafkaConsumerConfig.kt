package com.senok.notification.config

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import com.senok.coreeventpublisher.event.couple.CoupleEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig(
    private val props: KafkaProperties
) {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, CoupleEvent> {
        val cfg = props.buildConsumerProperties()
        cfg[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        cfg[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        cfg[JsonDeserializer.VALUE_DEFAULT_TYPE] = CoupleEvent::class.java.name
        return DefaultKafkaConsumerFactory(cfg)
    }

    @Bean
    fun coupleEventContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, CoupleEvent> =
        ConcurrentKafkaListenerContainerFactory<String, CoupleEvent>().apply {
            consumerFactory = consumerFactory()
            setConcurrency(1)
            isBatchListener = true
            containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
        }
}