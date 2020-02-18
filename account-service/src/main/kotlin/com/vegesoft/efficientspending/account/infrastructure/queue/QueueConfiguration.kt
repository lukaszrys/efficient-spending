package com.vegesoft.efficientspending.account.infrastructure.queue

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfiguration {

    @Bean
    fun myQueue(): Queue {
        return Queue("myQueue", false)
    }
}