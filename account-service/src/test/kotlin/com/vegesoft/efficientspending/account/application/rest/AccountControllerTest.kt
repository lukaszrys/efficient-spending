package com.vegesoft.efficientspending.account.application.rest

import com.vegesoft.efficientspending.account.application.query.AccountQuery
import com.vegesoft.efficientspending.account.application.query.CurrentAccountView
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.RuntimeException
import java.security.Principal
import java.util.*
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class AccountControllerTest {

    @MockK
    private lateinit var accountQuery: AccountQuery
    @InjectMockKs
    private lateinit var tested: AccountController

    @Test
    @DisplayName("Should get account view based on principal")
    fun shouldGetAccountView() {
        val principal = mockk<Principal>(relaxed = true)
        val view = mockk<CurrentAccountView>()
        every { accountQuery.findCurrentAccount(principal.name) } returns Optional.of(view)

        val result = tested.getAccountInformation(principal)

        assertEquals(view, result)
    }

    @Test
    @DisplayName("Should throw exception when no account view found")
    fun shouldThrowExceptionWhenQuery() {
        val principal = mockk<Principal>(relaxed = true)
        every { accountQuery.findCurrentAccount(principal.name) } returns Optional.empty()

        assertThrows<RuntimeException> { tested.getAccountInformation(principal) }
    }
}