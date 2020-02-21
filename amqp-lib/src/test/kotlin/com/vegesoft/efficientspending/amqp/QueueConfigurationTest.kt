package com.vegesoft.efficientspending.amqp

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import kotlin.test.assertEquals

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

        val result = tested.declarables()

        assertEquals(3, result.declarables.size)
    }

    @Test
    @DisplayName("Should create rabbit template with message converter")
    fun checkRabbitTemplate() {
        val messageConverter = mockk<MessageConverter>()
        val connectionFactory = mockk<ConnectionFactory>()

        val result = tested.rabbitTemplate(connectionFactory, messageConverter)

        assertEquals(messageConverter, result.messageConverter)
        assertEquals(connectionFactory, result.connectionFactory)
    }

    @Test
    @DisplayName("Should return simple message converter")
    fun checkConverter() {
        val result = tested.jsonMessageConverter()

        assertEquals(Jackson2JsonMessageConverter()::class.java, result::class.java)
    }
}