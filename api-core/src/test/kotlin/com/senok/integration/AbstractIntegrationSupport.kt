package com.senok.integration

import com.senok.ApiServerApplication
import com.senok.config.TestcontainersConfig
import com.senok.coredb.transaction.Tx
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = [ApiServerApplication::class, Tx::class, Tx.TxAdvice::class])
@Import(value = [TestcontainersConfig::class])
abstract class AbstractIntegrationSupport(
    body: BehaviorSpec.() -> Unit
): BehaviorSpec()