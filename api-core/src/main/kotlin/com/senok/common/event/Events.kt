package com.senok.common.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class Events(
    publisher: ApplicationEventPublisher,
) {
    init {
        Events.publisher = publisher
    }

    companion object {
        private lateinit var publisher: ApplicationEventPublisher

        fun raise(event: Any) {
            publisher.publishEvent(event)
        }
    }
}