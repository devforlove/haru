package com.senok.integration

import com.senok.ApiServerApplication
import com.senok.config.TestcontainersConfig
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = [ApiServerApplication::class])
@Import(value = [TestcontainersConfig::class])
abstract class AbstractIntegrationSupport(
    body: BehaviorSpec.() -> Unit
): BehaviorSpec(body) {

    init {
        extension(SpringExtension)
    }
}