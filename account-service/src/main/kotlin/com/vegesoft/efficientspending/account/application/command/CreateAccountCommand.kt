package com.vegesoft.efficientspending.account.application.command

import com.vegesoft.efficientspending.amqp.listener.QueueMessage
import com.vegesoft.efficientspending.cqrs.Command
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CreateAccountCommand(
        @NotEmpty
        val id: UUID,
        @NotEmpty
        val firstName: String,
        @NotEmpty
        val lastName: String,
        @Email
        @NotEmpty
        val email: String
) : Command, QueueMessage