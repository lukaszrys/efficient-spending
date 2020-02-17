package com.vegesoft.efficientspending.authorization.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.Email

@Document
data class AppUser(
        @Id
        val id: UUID,
        @Email
        val username: String,
        val password: String
)