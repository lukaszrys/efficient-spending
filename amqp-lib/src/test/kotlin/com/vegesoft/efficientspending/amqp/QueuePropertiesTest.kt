package com.vegesoft.efficientspending.amqp

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class QueuePropertiesTest {

    @MockK
    private lateinit var map: Map<String, String>
    @InjectMockKs
    private lateinit var tested: QueueProperties

    @Test
    @DisplayName("Should return properties as map")
    fun checkProperties() {
        val result = tested.queues

        assertEquals(map, result)
    }
}