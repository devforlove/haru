package com.senok.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig(
    private val producerFactory: ProducerFactory<String, String>
) {

    @Bean
    fun userRegisteredTopic(): NewTopic =
        TopicBuilder.name("user-registered-topic")
            .partitions(3)
            .replicas(3)
            .config("min.insync.replicas", "2")
            .build()

    @Bean
    fun userEventKafkaTemplate(): KafkaTemplate<String, String> =
        KafkaTemplate(producerFactory)
}