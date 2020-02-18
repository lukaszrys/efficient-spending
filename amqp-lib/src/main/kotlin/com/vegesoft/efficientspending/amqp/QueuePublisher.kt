package com.vegesoft.efficientspending.amqp

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.stereotype.Component

@Component
class QueuePublisher(
        private val rabbitTemplate: RabbitTemplate,
        private val queueProperties: QueueProperties,
        private val topicExchangeProvider: TopicExchangeProvider
) {
    init {
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
    }

    fun publish(serviceName: String, message: Any) {
        val value = queueProperties.queues["accountService"]
        value?.let {
            val accountServiceExchangeName = topicExchangeProvider.provideTopicExchangeName(value)

            rabbitTemplate.convertAndSend(accountServiceExchangeName, QueueType.MAIN.name, message)
        }
    }
}