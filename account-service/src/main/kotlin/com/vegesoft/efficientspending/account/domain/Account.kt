package com.vegesoft.efficientspending.account.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

const val ACCOUNT_COLLECTION_NAME = "account"

@Document(collation = ACCOUNT_COLLECTION_NAME)
data class Account(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val email: String
) {
    var balance: Balance = Balance()
        private set
    var status: AccountStatus = AccountStatus.CREATED
        private set

    private constructor(id: UUID, firstName: String, lastName: String, email: String, status: AccountStatus, balance: Balance)
            : this(id, firstName, lastName, email) {
        this.status = status
        this.balance = balance
    }
}