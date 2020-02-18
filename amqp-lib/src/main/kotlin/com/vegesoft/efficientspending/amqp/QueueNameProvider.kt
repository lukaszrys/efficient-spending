package com.vegesoft.efficientspending.amqp

import org.springframework.stereotype.Component

@Component
class QueueNameProvider {

    fun provideQueueName(name: String, queueType: QueueType): String {
        return "QUEUE.${name.toUpperCase()}.${queueType}"
    }
}