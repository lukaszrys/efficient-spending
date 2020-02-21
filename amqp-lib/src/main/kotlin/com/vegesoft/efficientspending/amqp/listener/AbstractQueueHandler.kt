package com.vegesoft.efficientspending.amqp.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.amqp.QueueType
import org.slf4j.LoggerFactory
import org.springframework.core.GenericTypeResolver


abstract class AbstractQueueHandler<T : QueueMessage>(
        private val objectMapper: ObjectMapper,
        private val queueNameProvider: QueueNameProvider,
        private val queueProperties: QueueProperties
) {

    private val logger = LoggerFactory.getLogger(AbstractQueueHandler::class.java)

    fun handleMessage(message: String) {
        try {
            val genericClass = getGenericClass()
            logger.info("Handling message: $message as $genericClass")
            processMessage(objectMapper.readValue(message, getGenericClass()))
        } catch (ex: Exception) {
            logger.error("Error when handling the message $message", ex)
        }
    }

    abstract fun processMessage(message: T)

    abstract fun getPropertyName(): String

    fun canHandle(queueName: String): Boolean {
        val value = queueProperties.queues[getPropertyName()]
        value?.let {
            return queueNameProvider.provideQueueName(value, QueueType.MAIN) == queueName
        }

        return false
    }

    private fun getGenericClass(): Class<T> {
        return GenericTypeResolver.resolveTypeArgument(javaClass, AbstractQueueHandler::class.java) as Class<T>
    }
}