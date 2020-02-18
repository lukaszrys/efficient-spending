package com.vegesoft.efficientspending.authorization.application.controller

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserRequest
import com.vegesoft.efficientspending.authorization.application.controller.mapper.UserCommandMapper
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
import org.junit.jupiter.api.extension.ExtendWith
import java.security.Principal
import java.util.*
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class UserControllerTest {
    @RelaxedMockK
    private lateinit var commandBus: CommandBus
    @MockK
    private lateinit var userCommandMapper: UserCommandMapper;
    @InjectMockKs
    private lateinit var tested: UserController

    @Test
    @DisplayName("Should return principal")
    fun shouldReturnPrincipal() {
        val principal = mockk<Principal>()

        val result = tested.getCurrentlyLoggedUser(principal)

        assertEquals(principal, result)
    }

    @Test
    @DisplayName("Should dispatch create app user command")
    fun shouldDispatchCreateAppUserCommand() {
        val request = CreateAppUserRequest("firstName", "lastName", "email@email", "password")
        val command = CreateAppUserCommand(UUID.randomUUID(), request)
        every { userCommandMapper.createCommand(any(), request) } returns command

        tested.createUser(request)

        verify { commandBus.dispatch(command) }
    }

}