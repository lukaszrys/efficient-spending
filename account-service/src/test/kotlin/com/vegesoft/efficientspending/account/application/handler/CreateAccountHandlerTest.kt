package com.vegesoft.efficientspending.account.application.handler

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import com.vegesoft.efficientspending.account.domain.Account
import com.vegesoft.efficientspending.account.domain.AccountRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
internal class CreateAccountHandlerTest {

    @MockK
    lateinit var accountRepository: AccountRepository
    @InjectMockKs
    lateinit var tested: CreateAccountHandler

    @Test
    @DisplayName("Should handle command by creating and saving an account")
    fun shouldHandleCommand_andSaveAccount() {
        val request = CreateAccountRequest("firstName", "lastName", "email@email.com", "password")
        val id = UUID.randomUUID()
        val command = CreateAccountCommand(id, request)
        val account = Account(id, request.firstName, request.lastName, request.email)
        every { accountRepository.save(eq(account)) } returns account

        tested.createAccount(command)

        verify { accountRepository.save(account) }
    }
}