package com.senok.integration

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
abstract class AbstractIntegrationSupport(): BehaviorSpec() {

    init {

    }
}