package com.senok.apicore.common.integration

import com.senok.ApiServerApplication
import com.senok.apicore.common.config.TestDatabaseContainersConfig
import com.senok.coreeventpublisher.TestKafkaContainerContext
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(classes = [ApiServerApplication::class], properties = ["spring.profiles.active=test"])
@ContextConfiguration(initializers = [TestKafkaContainerContext::class])
@Import(value = [TestDatabaseContainersConfig::class, TestKafkaContainerContext::class])
abstract class AbstractIntegrationSupport(
    body: DescribeSpec.() -> Unit
): DescribeSpec(body) {

    init {
        extension(SpringExtension)
    }
}