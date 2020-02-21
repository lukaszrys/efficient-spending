package com.vegesoft.efficientspending.amqp.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties

class ConcreteQueueHandler(
        objectMapper: ObjectMapper,
        queueNameProvider: QueueNameProvider,
        queueProperties: QueueProperties,
        private val propertyName: String
) : AbstractQueueHandler<QueueMessage>(objectMapper, queueNameProvider, queueProperties) {
    override fun processMessage(message: QueueMessage) {
    }

    override fun getPropertyName() = propertyName
}