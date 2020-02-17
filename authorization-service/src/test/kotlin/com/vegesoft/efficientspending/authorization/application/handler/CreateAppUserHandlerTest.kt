package com.vegesoft.efficientspending.authorization.application.handler

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import com.vegesoft.efficientspending.authorization.domain.AppUser
import com.vegesoft.efficientspending.authorization.domain.AppUserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
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
    @InjectMockKs
    private lateinit var tested: CreateAppUserHandler

    @Test
    @DisplayName("Should create app user and save in repository")
    fun shouldCreateAppUser() {
        val command = CreateAppUserCommand(UUID.randomUUID(), "email@email", "password")
        val encodedPassword = "encodedPassword"
        val appUser = AppUser(command.id, command.username, encodedPassword)
        every { passwordEncoder.encode(command.password) } returns encodedPassword
        every { appUserRepository.save(appUser) } returns appUser

        tested.createAccount(command)

        verify { appUserRepository.save(appUser) }
    }
}