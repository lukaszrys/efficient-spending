package com.vegesoft.efficientspending.authorization.application.command

import com.vegesoft.efficientspending.amqp.listener.QueueMessage
import com.vegesoft.efficientspending.cqrs.Command
import java.util.*
import javax.validation.constraints.NotEmpty

data class CreateAppUserCommand(
        @NotEmpty
        val id: UUID,
        @NotEmpty
        val username: String,
        @NotEmpty
        val password: String
) : Command, QueueMessage