package com.senok.apicore.common.domain

import com.senok.apicore.common.event.Events
import com.senok.apicore.common.transaction.Tx
import kotlin.properties.Delegates

abstract class DomainModel<T> {
    var id: Long by Delegates.notNull()
        protected set
    
    fun assignId(id: Long): T {
        this.id = id
        return this as T
    }
    
    protected fun publishEvent(event: Any) {
        Tx.writable {
            Events.raise(event)
        }
    }
}