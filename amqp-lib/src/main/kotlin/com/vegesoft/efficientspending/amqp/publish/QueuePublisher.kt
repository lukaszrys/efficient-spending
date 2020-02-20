package com.vegesoft.efficientspending.amqp.publish

import com.fasterxml.jackson.databind.ObjectMapper
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.amqp.QueueType
import com.vegesoft.efficientspending.amqp.TopicExchangeProvider
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class QueuePublisher(
        private val rabbitTemplate: RabbitTemplate,
        private val queueProperties: QueueProperties,
        private val topicExchangeProvider: TopicExchangeProvider,
        private val objectMapper: ObjectMapper
) {

    fun publish(serviceName: String, message: Any) {
        val value = queueProperties.queues[serviceName]
        value?.let {
            val accountServiceExchangeName = topicExchangeProvider.provideTopicExchangeName(value)
            rabbitTemplate.convertAndSend(accountServiceExchangeName, QueueType.MAIN.name, objectMapper.writeValueAsString(message))
        }
    }
}