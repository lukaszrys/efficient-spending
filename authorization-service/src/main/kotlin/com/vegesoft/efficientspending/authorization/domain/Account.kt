package com.vegesoft.efficientspending.authorization.domain

import java.util.*

data class Account(
        val id: UUID,
        val email: String,
        val firstName: String,
        val lastName: String
)