package com.vegesoft.efficientspending.account.infrastructure.configuration

import com.vegesoft.efficientspending.amqp.QueueConfiguration
import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [QueueConfiguration::class])
class CQRSConfiguration(
        private val publisher: ApplicationEventPublisher
) {

    @Bean
    fun commandBus(): CommandBus {
        return CommandBus(publisher)
    }
}