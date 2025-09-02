package com.senok.apicore.common.transaction

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
        
        fun <T> readOnly(function: () -> T): T {
            return txAdvice.readOnly(function)
        }
        
        fun <T> writable(function: () -> T): T {
            return txAdvice.writable(function)
        }
    }

    @Component
    class TxAdvice {
        
        @Transactional(readOnly = true)
        fun <T> readOnly(function: () -> T): T {
            return function()
        }
        
        @Transactional
        fun <T> writable(function: () -> T): T {
            return function()
        }
    }
}