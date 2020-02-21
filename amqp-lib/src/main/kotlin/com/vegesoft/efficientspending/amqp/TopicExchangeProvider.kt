package com.vegesoft.efficientspending.amqp

import org.springframework.stereotype.Component

@Component
class TopicExchangeProvider {

    fun provideTopicExchangeName(name: String): String {
        return "EXCHANGE.${name.toUpperCase()}"
    }
}