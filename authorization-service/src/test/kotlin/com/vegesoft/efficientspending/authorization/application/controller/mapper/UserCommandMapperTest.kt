package com.vegesoft.efficientspending.authorization.application.controller.mapper

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserRequest
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class UserCommandMapperTest {
    private val tested = UserCommandMapper()

    @Test
    @DisplayName("Should map to create app user command")
    fun shouldMapToCreateAppUserCommand() {
        val id = UUID.randomUUID();
        val request = mockk<CreateAppUserRequest>()

        val result = tested.createCommand(id, request)

        assertEquals(id, result.id)
        assertEquals(request, result.data)
    }
}