package com.senok.coreeventpublisher.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig(
    private val props: KafkaProperties
) {
    
    @Bean
    fun coupleEventKafkaTemplate(): KafkaTemplate<String, Any> {
        val props = props.buildProducerProperties()
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.RETRIES_CONFIG] = 5
        props[ProducerConfig.LINGER_MS_CONFIG] = 10
        return KafkaTemplate(DefaultKafkaProducerFactory(props))
    }

    @Bean
    fun deviceEventKafkaTemplate(): KafkaTemplate<String, Any> {
        val props = props.buildProducerProperties()
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.RETRIES_CONFIG] = 5
        props[ProducerConfig.LINGER_MS_CONFIG] = 10
        return KafkaTemplate(DefaultKafkaProducerFactory(props))
    }
    
    
    @Bean
    fun coupleMessageEventKafkaTemplate(): KafkaTemplate<String, Any> {
        val props = props.buildProducerProperties()
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.RETRIES_CONFIG] = 5
        props[ProducerConfig.LINGER_MS_CONFIG] = 10
        return KafkaTemplate(DefaultKafkaProducerFactory(props))
    }
    
    @Bean
    fun coupleRequestEventKafkaTemplate(): KafkaTemplate<String, Any> {
        val props = props.buildProducerProperties()
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.RETRIES_CONFIG] = 5
        props[ProducerConfig.LINGER_MS_CONFIG] = 10
        return KafkaTemplate(DefaultKafkaProducerFactory(props))
    }
}