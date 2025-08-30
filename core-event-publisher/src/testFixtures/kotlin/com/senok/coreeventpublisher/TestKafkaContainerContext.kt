package com.senok.coreeventpublisher

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.kafka.KafkaContainer
import org.testcontainers.utility.DockerImageName

class TestKafkaContainerContext: ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        @Container
        @JvmStatic
        val kafka = KafkaContainer(DockerImageName.parse("apache/kafka:3.7.0")).apply { start() }
    }

    override fun initialize(ctx: ConfigurableApplicationContext) {
        TestPropertyValues.of(
            "spring.kafka.bootstrap-servers=${kafka.bootstrapServers}"
        ).applyTo(ctx.environment)
    }
}