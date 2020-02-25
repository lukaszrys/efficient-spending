package com.vegesoft.efficientspending.account.application.controller

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import com.vegesoft.efficientspending.account.application.controller.mapper.AccountCommandMapper
import com.vegesoft.efficientspending.account.application.query.AccountQuery
import com.vegesoft.efficientspending.account.application.query.CurrentAccountView
import com.vegesoft.efficientspending.cqrs.CommandBus
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.security.Principal
import java.util.*
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class AccountControllerTest {

    @MockK
    private lateinit var accountQuery: AccountQuery
    @MockK
    private lateinit var accountCommandMapper: AccountCommandMapper
    @RelaxedMockK
    private lateinit var commandBus: CommandBus
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

    @Test
    @DisplayName("Should dispatch create account command")
    fun shouldDispatchCreateAppUserCommand() {
        val request = CreateAccountRequest("firstName", "lastName", "email@email", "password")
        val command = CreateAccountCommand(UUID.randomUUID(), request)
        every { accountCommandMapper.createCommand(any(), request) } returns command

        tested.createUser(request)

        verify { commandBus.dispatch(command) }
    }
}