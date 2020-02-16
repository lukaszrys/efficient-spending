package com.vegesoft.efficientspending.cqrs

import org.springframework.context.ApplicationEventPublisher

class CommandBus(
        private val publisher: ApplicationEventPublisher
) {
    fun dispatch(command: Command) {
        publisher.publishEvent(command)
    }
}