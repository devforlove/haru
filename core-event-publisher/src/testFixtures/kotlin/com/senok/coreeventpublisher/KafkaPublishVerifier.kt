package com.senok.coreeventpublisher

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.assertj.core.api.Assertions.assertThat
import org.awaitility.Awaitility.await
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.time.Duration

object KafkaPublishVerifier {

    inline fun <reified T> verify(
        topic: String,
        crossinline verify: (T) -> Unit,
    ) {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to TestKafkaContainerContext.kafka.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "test-consumer-${System.nanoTime()}",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
        )

        val valueDeserializer = JsonDeserializer(T::class.java).apply {
            addTrustedPackages("*")
        }

        KafkaConsumer(
            props,
            StringDeserializer(),
            valueDeserializer
        ).use { consumer ->
            consumer.subscribe(listOf(topic))

            await().atMost(Duration.ofSeconds(5)).untilAsserted {
                val records = consumer.poll(Duration.ofMillis(500))
                require(records.count() > 0) { "No records polled yet" }

                val record = records.last()
                verify(record.value())
                assertThat(record.topic()).isEqualTo(topic)
            }
        }
    }
}