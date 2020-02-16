package com.vegesoft.efficientspending.account.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

internal class AccountTest {

    @Test
    @DisplayName("Should create account with CREATED status and 0 balance")
    fun shouldCreateAccountWithCreatedStatusAnd0Balance() {
        val id = UUID.randomUUID();
        val firstName = "Alan";
        val lastName = "Turing";
        val email = "howtohackenigma@turing.com";

        val createdAccount = AccountProvider.createdAccount(id, firstName, lastName, email)

        assertEquals(id, createdAccount.id)
        assertEquals(AccountStatus.CREATED, createdAccount.status)
        assertEquals(firstName, createdAccount.firstName)
        assertEquals(lastName, createdAccount.lastName)
        assertEquals(email, createdAccount.email)
        assertEquals(Balance(), createdAccount.balance)
    }
}