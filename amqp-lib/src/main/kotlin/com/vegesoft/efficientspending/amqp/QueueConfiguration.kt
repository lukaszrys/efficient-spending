package com.vegesoft.efficientspending.amqp

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.BindingBuilder.bind
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@Configuration
@ComponentScan("com.vegesoft.efficientspending.amqp")
class QueueConfiguration(
        private val queueProperties: QueueProperties,
        private val topicExchangeProvider: TopicExchangeProvider,
        private val queueNameProvider: QueueNameProvider
) {

    @Bean
    fun jsonMessageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, messageConverter: MessageConverter): RabbitTemplate {
        val template = RabbitTemplate(connectionFactory)
        template.messageConverter = messageConverter
        return template
    }

    @Bean
    fun declarables(): Declarables {
        val list = queueProperties.queues.map { (_, queueName) ->
            val topicExchange = TopicExchange(topicExchangeProvider.provideTopicExchangeName(queueName))
            val queue = Queue(queueNameProvider.provideQueueName(queueName, QueueType.MAIN), false)

            return Declarables(queue, topicExchange, bind(queue).to(topicExchange).with(QueueType.MAIN.name))
        }

        return Declarables(list)
    }
}