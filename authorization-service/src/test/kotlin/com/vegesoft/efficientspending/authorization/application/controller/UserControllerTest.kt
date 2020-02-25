package com.vegesoft.efficientspending.authorization.application.controller

import com.vegesoft.efficientspending.cqrs.CommandBus
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.security.Principal
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class UserControllerTest {
    @RelaxedMockK
    private lateinit var commandBus: CommandBus
    @InjectMockKs
    private lateinit var tested: UserController

    @Test
    @DisplayName("Should return principal")
    fun shouldReturnPrincipal() {
        val principal = mockk<Principal>()

        val result = tested.getCurrentlyLoggedUser(principal)

        assertEquals(principal, result)
    }

}