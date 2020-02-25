package com.vegesoft.efficientspending.account.application.command

import com.vegesoft.efficientspending.amqp.listener.QueueMessage
import com.vegesoft.efficientspending.cqrs.Command
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateAccountCommand(
        @NotEmpty
        val id: UUID,
        @Valid
        @NotNull
        val data: CreateAccountRequest
) : Command, QueueMessage