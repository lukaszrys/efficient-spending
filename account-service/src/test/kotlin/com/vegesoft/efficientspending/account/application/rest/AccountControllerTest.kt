package com.vegesoft.efficientspending.account.application.rest

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import com.vegesoft.efficientspending.account.application.rest.mapper.AccountCommandMapper
import com.vegesoft.efficientspending.cqrs.CommandBus
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
internal class AccountControllerTest {

    @RelaxedMockK
    lateinit var commandBus: CommandBus
    @RelaxedMockK
    lateinit var accountCommandMapper: AccountCommandMapper
    @InjectMockKs
    lateinit var tested: AccountController

    @Test
    @DisplayName("Should dispatch create account command and return id")
    fun shouldDispatchCreateAccountCommand() {
        val request = CreateAccountRequest("firstName", "lastName", "email@email.com")
        val command = CreateAccountCommand(UUID.randomUUID(), request)
        every { accountCommandMapper.createCommand(any(), request) } returns command

        val result = tested.createAccount(request)

        verify { commandBus.dispatch(command) }
        assertNotNull(result.id)
    }
}