package com.vegesoft.efficientspending.amqp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class TopicExchangeProviderTest {
    private val tested = TopicExchangeProvider()

    @Test
    @DisplayName("Should return exchange name")
    fun shouldReturnExchangeName() {
        val rawName = "test"

        val result = tested.provideTopicExchangeName(rawName)

        assertEquals("EXCHANGE.${rawName.toUpperCase()}", result)
    }
}