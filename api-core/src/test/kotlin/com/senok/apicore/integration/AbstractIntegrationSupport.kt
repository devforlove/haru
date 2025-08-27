package com.senok.apicore.integration

import com.senok.ApiServerApplication
import com.senok.apicore.config.TestcontainersConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = [ApiServerApplication::class])
@Import(value = [TestcontainersConfig::class])
abstract class AbstractIntegrationSupport(
    body: DescribeSpec.() -> Unit
): DescribeSpec(body) {

    init {
        extension(SpringExtension)
    }
}