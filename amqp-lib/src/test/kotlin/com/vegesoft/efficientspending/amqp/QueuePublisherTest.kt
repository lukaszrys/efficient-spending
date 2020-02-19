package com.vegesoft.efficientspending.amqp

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.amqp.rabbit.core.RabbitTemplate

@ExtendWith(MockKExtension::class)
internal class QueuePublisherTest {
    @RelaxedMockK
    private lateinit var rabbitTemplate: RabbitTemplate
    @MockK
    private lateinit var queueProperties: QueueProperties
    @MockK
    private lateinit var topicExchangeProvider: TopicExchangeProvider
    @InjectMockKs
    private lateinit var tested: QueuePublisher

    @Test
    @DisplayName("Should publish message to main queue")
    fun shouldPublishMessage() {
        val key = "key"
        val value = "value"
        val message = "message"
        val exchangeName = "exchangeName"

        every { queueProperties.queues } returns mapOf(key to value)
        every { topicExchangeProvider.provideTopicExchangeName(value) } returns exchangeName

        tested.publish(key, message)

        verify { rabbitTemplate.convertAndSend(exchangeName, QueueType.MAIN.name, message) }
    }
}