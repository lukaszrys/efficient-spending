package com.vegesoft.efficientspending.authorization.application.command

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CreateAppUserRequest (
        @NotEmpty
        val firstName: String,
        @NotEmpty
        val lastName: String,
        @Email
        @NotEmpty
        val email: String,
        @NotEmpty
        val password: String
)