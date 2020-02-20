package com.vegesoft.efficientspending.account.application.rest

import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.security.Principal
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class AccountControllerTest {

    lateinit var tested: AccountController

    @Test
    @DisplayName("Should get principal")
    fun shouldGetPrincipal() {
        val principal = mockk<Principal>()

        val result = tested.getAccountInformation(principal)

        assertEquals(principal, result)
    }
}