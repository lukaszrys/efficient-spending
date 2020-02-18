package com.vegesoft.efficientspending.authorization.application.command

import com.vegesoft.efficientspending.cqrs.Command
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

data class CreateAppUserCommand(
        @NotEmpty
        val id: UUID,
        @Valid
        val data: CreateAppUserRequest
) : Command