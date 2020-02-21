package com.vegesoft.efficientspending.account.application.queuehandler

import com.fasterxml.jackson.databind.ObjectMapper
import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.amqp.listener.AbstractQueueHandler
import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.stereotype.Component

@Component
class CreateAccountMessageHandler(
        objectMapper: ObjectMapper,
        queueNameProvider: QueueNameProvider,
        queueProperties: QueueProperties,
        private val commandBus: CommandBus
) : AbstractQueueHandler<CreateAccountCommand>(objectMapper, queueNameProvider, queueProperties) {

    override fun processMessage(message: CreateAccountCommand) = commandBus.dispatch(message)

    override fun getPropertyName() = "accountService"
}