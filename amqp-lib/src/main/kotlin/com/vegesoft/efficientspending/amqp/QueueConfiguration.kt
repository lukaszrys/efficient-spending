package com.vegesoft.efficientspending.amqp

import org.springframework.amqp.core.BindingBuilder.bind
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@Configuration
@ComponentScan("com.vegesoft.efficientspending.amqp")
class QueueConfiguration(
        val queueProperties: QueueProperties,
        val topicExchangeProvider: TopicExchangeProvider,
        val queueNameProvider: QueueNameProvider
) {

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