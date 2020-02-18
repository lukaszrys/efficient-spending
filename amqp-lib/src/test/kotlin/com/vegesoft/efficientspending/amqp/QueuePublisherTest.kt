package com.vegesoft.efficientspending.amqp

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
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
    private lateinit var queuePublisher: QueuePublisher

    @Test
    @DisplayName("Should publish message to main queue")
    fun shouldPublishMessage() {

    }
}