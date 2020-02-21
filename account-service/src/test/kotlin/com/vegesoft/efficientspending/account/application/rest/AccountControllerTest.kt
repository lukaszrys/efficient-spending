package com.vegesoft.efficientspending.account.application.rest

import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.security.Principal
import kotlin.test.assertEquals

internal class AccountControllerTest {

    private val tested = AccountController()

    @Test
    @DisplayName("Should get principal")
    fun shouldGetPrincipal() {
        val principal = mockk<Principal>()

        val result = tested.getAccountInformation(principal)

        assertEquals(principal, result)
    }
}