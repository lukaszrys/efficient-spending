package com.vegesoft.efficientspending.authorization.application.handler

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserRequest
import com.vegesoft.efficientspending.authorization.domain.Account
import com.vegesoft.efficientspending.authorization.domain.AppUser
import com.vegesoft.efficientspending.authorization.domain.repository.AccountRepository
import com.vegesoft.efficientspending.authorization.domain.repository.AppUserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
internal class CreateAppUserHandlerTest {

    @MockK
    private lateinit var appUserRepository: AppUserRepository
    @MockK
    private lateinit var passwordEncoder: BCryptPasswordEncoder
    @RelaxedMockK
    private lateinit var accountRepository: AccountRepository
    @InjectMockKs
    private lateinit var tested: CreateAppUserHandler

    @Test
    @DisplayName("Should create app user and save in repository")
    fun shouldCreateAppUser() {
        val request = CreateAppUserRequest("firstName", "lastName", "email@email", "password")
        val command = CreateAppUserCommand(UUID.randomUUID(), request)
        val encodedPassword = "encodedPassword"
        val appUser = AppUser(command.id, request.email, encodedPassword)
        val account = Account(appUser.id, appUser.username, request.firstName, request.lastName)
        every { passwordEncoder.encode(request.password) } returns encodedPassword
        every { appUserRepository.save(appUser) } returns appUser

        tested.createAccount(command)

        verify { appUserRepository.save(appUser) }
        verify { accountRepository.save(account) }
    }
}