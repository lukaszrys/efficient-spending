package com.vegesoft.efficientspending.account.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Account(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val email: String
) {
    val balance: Balance = Balance()
    val status: AccountStatus = AccountStatus.CREATED
}