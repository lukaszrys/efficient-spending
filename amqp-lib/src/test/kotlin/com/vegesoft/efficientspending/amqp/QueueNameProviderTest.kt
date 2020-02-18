package com.vegesoft.efficientspending.amqp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class QueueNameProviderTest {
    private val tested = QueueNameProvider()

    @Test
    @DisplayName("Should return queue name")
    fun shouldReturnQueueName() {
        val rawName = "test"
        val type = QueueType.MAIN

        val result = tested.provideQueueName(rawName, type)

        assertEquals("QUEUE.${rawName.toUpperCase()}.${type}", result)
    }
}