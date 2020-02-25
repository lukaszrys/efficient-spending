package com.vegesoft.efficientspending.account.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class AuthorizationUser(
        @Id
        val id: UUID,
        val username: String,
        val password: String
)