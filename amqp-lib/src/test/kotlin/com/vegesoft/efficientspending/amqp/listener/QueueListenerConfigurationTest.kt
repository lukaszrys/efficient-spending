package com.vegesoft.efficientspending.amqp.listener

import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.amqp.QueueType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import java.util.*

internal class QueueListenerConfigurationTest {
    private lateinit var handler: AbstractQueueHandler<*>
    private lateinit var connectionFactory: ConnectionFactory
    private lateinit var beanFactory: ConfigurableBeanFactory
    private lateinit var queueProperties: QueueProperties
    private lateinit var queueNameProvider: QueueNameProvider
    private lateinit var messageConverter: MessageConverter
    private lateinit var tested: QueueListenerConfiguration

    @BeforeEach
    fun setUp() {
        handler = mockk()
        connectionFactory = mockk()
        beanFactory = mockk(relaxed = true)
        queueProperties = mockk()
        queueNameProvider = mockk()
        messageConverter = mockk()

        tested = QueueListenerConfiguration(Optional.of(listOf(handler, mockk(relaxed = true))), connectionFactory, beanFactory,
                queueProperties, queueNameProvider, messageConverter)
    }

    @Test
    @DisplayName("Should register handler for queue")
    fun checkRegisterHandlers() {
        val rawQueueName = "rawQueueName"
        val queueName = "queueName"
        every { queueProperties.queues } returns mapOf("key" to rawQueueName)
        every { queueNameProvider.provideQueueName(rawQueueName, QueueType.MAIN) } returns queueName
        every { handler.canHandle(queueName) } returns true

        tested.registerHandlers()

        verify { beanFactory.registerSingleton(handler::class::simpleName.name, any<SimpleMessageListenerContainer>()) }
    }
}