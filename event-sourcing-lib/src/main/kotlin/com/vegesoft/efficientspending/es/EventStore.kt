package com.vegesoft.efficientspending.es

import reactor.core.publisher.Flux

interface EventStore<T> {
    fun save(event: Event<T>)

    fun findEvents(id: T): Flux<Event<T>>
}