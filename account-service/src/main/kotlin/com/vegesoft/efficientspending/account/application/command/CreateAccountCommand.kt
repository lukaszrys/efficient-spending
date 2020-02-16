package com.vegesoft.efficientspending.account.application.command

import com.vegesoft.efficientspending.cqrs.Command
import java.util.*
import javax.validation.constraints.NotEmpty

data class CreateAccountCommand(
        @NotEmpty
        val id: UUID,
        val data: CreateAccountRequest
) : Command