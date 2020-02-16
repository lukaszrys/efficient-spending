package com.vegesoft.efficientspending.account.domain

import java.util.*

object AccountProvider {
    fun createdAccount(id: UUID, firstName: String, lastName: String, email: String): Account {
        return Account(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = email
        )
    }

    fun createdAccount(id: UUID): Account {
        return createdAccount(
                id = id,
                firstName = "firstName",
                lastName = "lastName",
                email = "email@email"
        )
    }
}