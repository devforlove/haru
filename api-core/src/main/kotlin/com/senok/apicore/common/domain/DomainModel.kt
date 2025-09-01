package com.senok.apicore.common.domain

import com.senok.apicore.common.event.Events
import kotlin.properties.Delegates

abstract class DomainModel<T> {
    var id: Long by Delegates.notNull()
        protected set
    
    abstract fun assignId(id: Long): T
    
    protected fun publishEvent(event: Any) {
        Events.raise(event)
    }
}