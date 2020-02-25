package com.vegesoft.efficientspending.authorization.application.queuehandler

import com.fasterxml.jackson.databind.ObjectMapper
import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.amqp.listener.AbstractQueueHandler
import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.stereotype.Component

@Component
class CreateUserMessageHandler(
        objectMapper: ObjectMapper,
        queueNameProvider: QueueNameProvider,
        queueProperties: QueueProperties,
        private val commandBus: CommandBus
) : AbstractQueueHandler<CreateAppUserCommand>(objectMapper, queueNameProvider, queueProperties) {

    override fun processMessage(message: CreateAppUserCommand) = commandBus.dispatch(message)

    override fun getPropertyName() = "accountService"
}