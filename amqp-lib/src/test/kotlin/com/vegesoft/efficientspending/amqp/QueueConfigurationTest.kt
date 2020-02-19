package com.vegesoft.efficientspending.amqp

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class QueueConfigurationTest {
    @MockK
    private lateinit var queueProperties: QueueProperties
    @MockK
    private lateinit var topicExchangeProvider: TopicExchangeProvider
    @MockK
    private lateinit var queueNameProvider: QueueNameProvider
    @InjectMockKs
    private lateinit var tested: QueueConfiguration

    @Test
    @DisplayName("Should create proper declarables")
    fun shouldCreteProperDeclarables() {
        val key = "key"
        val value = "value"
        val exchangeName = "exchangeName"
        val queueName = "queueName"

        every { queueProperties.queues } returns mapOf(key to value)
        every { topicExchangeProvider.provideTopicExchangeName(value) } returns exchangeName
        every { queueNameProvider.provideQueueName(value, QueueType.MAIN) } returns queueName

        tested.declarables()
    }
}