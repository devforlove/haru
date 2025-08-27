package com.senok.common.transaction

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Tx(
    _txAdvice: TxAdvice,
) {

    init {
        txAdvice = _txAdvice
    }

    companion object {
        private lateinit var txAdvice: TxAdvice

        fun <T> writable(function: () -> T): T {
            return txAdvice.writable(function)
        }
    }

    @Component
    class TxAdvice {

        @Transactional
        fun <T> writable(function: () -> T): T {
            return function()
        }
    }
}