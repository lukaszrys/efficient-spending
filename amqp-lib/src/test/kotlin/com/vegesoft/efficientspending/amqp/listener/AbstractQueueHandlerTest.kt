package com.vegesoft.efficientspending.amqp.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.amqp.QueueType
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
internal class AbstractQueueHandlerTest {
    private lateinit var objectMapper: ObjectMapper
    private lateinit var queueNameProvider: QueueNameProvider
    private lateinit var queueProperties: QueueProperties
    private lateinit var serviceName: String
    private lateinit var tested: ConcreteQueueHandler

    @BeforeEach
    fun setUp() {
        objectMapper = mockk()
        queueNameProvider = mockk()
        queueProperties = mockk()
        serviceName = "testService"
        tested = ConcreteQueueHandler(objectMapper, queueNameProvider, queueProperties, serviceName)
    }

    @Test
    @DisplayName("Should process the message")
    fun shouldProcessTheMessage() {
        val message = "message"

        tested.handleMessage(message)

        verify { objectMapper.readValue(message, QueueMessage::class.java) }
    }

    @Test
    @DisplayName("Should could handle the message")
    fun shouldCouldHandleTheMessage() {
        val queueName = "queueName"

        every { queueProperties.queues } returns mapOf(serviceName to queueName)
        every { queueNameProvider.provideQueueName(queueName, QueueType.MAIN) } returns queueName

        val result = tested.canHandle(queueName)

        assertTrue { result }
    }
}