package com.vegesoft.efficientspending.account.application.command

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CreateAccountRequest (
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