package com.vegesoft.efficientspending.authorization.infrastructure.configuration

import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CQRSConfiguration(
        private val publisher: ApplicationEventPublisher
) {

    @Bean
    fun commandBus(): CommandBus {
        return CommandBus(publisher)
    }
}