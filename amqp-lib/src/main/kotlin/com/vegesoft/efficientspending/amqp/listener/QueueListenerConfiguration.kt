package com.vegesoft.efficientspending.amqp.listener

import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.amqp.QueueType
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.annotation.PostConstruct


@Configuration
class QueueListenerConfiguration(
        private val handlers: Optional<Collection<AbstractQueueHandler<*>>>,
        private val connectionFactory: ConnectionFactory,
        private val beanFactory: ConfigurableBeanFactory,
        private val queueProperties: QueueProperties,
        private val queueNameProvider: QueueNameProvider,
        private val messageConverter: MessageConverter
) {
    @PostConstruct
    fun registerHandlers() {
        handlers.ifPresent { queueHandlers ->
            queueProperties.queues.forEach { (_, rawQueueName) ->
                val queueName = queueNameProvider.provideQueueName(rawQueueName, QueueType.MAIN)
                queueHandlers.stream()
                        .filter { handler -> handler.canHandle(queueName) }
                        .findFirst()
                        .ifPresent { handler ->
                            beanFactory.registerSingleton(getHandlerName(handler).name,
                                    buildMessageListenerContainer(connectionFactory, listenerAdapter(handler), queueName))
                        }
            }
        }
    }

    private fun getHandlerName(handler: AbstractQueueHandler<*>) = handler::class::simpleName

    private fun buildMessageListenerContainer(connectionFactory: ConnectionFactory,
                                              listenerAdapter: MessageListenerAdapter, queueName: String): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.setMessageListener(listenerAdapter)
        return container
    }

    private fun listenerAdapter(receiver: Any): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, messageConverter)
    }
}