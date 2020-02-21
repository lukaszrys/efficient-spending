package com.vegesoft.efficientspending.amqp

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "amqp")
class QueueProperties {
    lateinit var queues: Map<String, String>
}