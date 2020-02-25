package com.vegesoft.efficientspending.account.application.controller.mapper

import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class AccountCommandMapperTest {
    private val tested = AccountCommandMapper()

    @Test
    @DisplayName("Should map to create app user command")
    fun shouldMapToCreateAppUserCommand() {
        val id = UUID.randomUUID();
        val request = mockk<CreateAccountRequest>()

        val result = tested.createCommand(id, request)

        assertEquals(id, result.id)
        assertEquals(request, result.data)
    }
}