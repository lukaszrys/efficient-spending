package com.vegesoft.efficientspending.account.application.handler

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import com.vegesoft.efficientspending.account.domain.Account
import com.vegesoft.efficientspending.account.domain.AccountRepository
import com.vegesoft.efficientspending.account.domain.AuthorizationUser
import com.vegesoft.efficientspending.account.domain.repository.AuthorizationUserRepository
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
import java.util.*

@ExtendWith(MockKExtension::class)
internal class CreateAccountHandlerTest {

    @MockK
    lateinit var accountRepository: AccountRepository
    @RelaxedMockK
    lateinit var authorizationUserRepository: AuthorizationUserRepository
    @InjectMockKs
    lateinit var tested: CreateAccountHandler

    @Test
    @DisplayName("Should handle command by creating and saving an account")
    fun shouldHandleCommand_andSaveAccount() {
        val id = UUID.randomUUID()
        val request = CreateAccountRequest("firstName", "lastName", "email@email.com", "password")
        val command = CreateAccountCommand(id, request)
        val account = Account(id, request.firstName, request.lastName, request.email)
        val authorizationUser = AuthorizationUser(id, request.email, request.password)
        every { accountRepository.save(eq(account)) } returns account
        every { accountRepository.findAccountByEmail(request.email) } returns Optional.empty()

        tested.createAccount(command)

        verify { accountRepository.save(account) }
        verify { authorizationUserRepository.save(authorizationUser) }
    }

    @Test
    @DisplayName("Should throw exception when email already exists")
    fun shouldValidateEmail() {
        val id = UUID.randomUUID()
        val request = CreateAccountRequest("firstName", "lastName", "email@email.com", "password")
        val command = CreateAccountCommand(id, request)

        every { accountRepository.findAccountByEmail(request.email) } returns Optional.of(mockk())

        assertThrows<RuntimeException> { tested.createAccount(command) }
    }
}