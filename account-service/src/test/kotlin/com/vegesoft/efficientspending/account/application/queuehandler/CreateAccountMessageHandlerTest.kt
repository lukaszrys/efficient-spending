package com.vegesoft.efficientspending.account.application.queuehandler

import com.fasterxml.jackson.databind.ObjectMapper
import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.amqp.QueueNameProvider
import com.vegesoft.efficientspending.amqp.QueueProperties
import com.vegesoft.efficientspending.cqrs.CommandBus
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class CreateAccountMessageHandlerTest {

    @RelaxedMockK
    private lateinit var objectMapper: ObjectMapper
    @RelaxedMockK
    private lateinit var queueNameProvider: QueueNameProvider
    @RelaxedMockK
    private lateinit var queueProperties: QueueProperties
    @RelaxedMockK
    private lateinit var commandBus: CommandBus
    @InjectMockKs
    private lateinit var tested: CreateAccountMessageHandler

    @Test
    @DisplayName("Should return account service name")
    fun shouldReturnAccountServiceName() {
        val result = tested.getPropertyName()

        assertEquals("accountService", result)
    }

    @Test
    @DisplayName("Should dispatch message to commandBus")
    fun shouldDispatchMessageToCommandBus() {
        val message = mockk<CreateAccountCommand>()

        tested.processMessage(message)

        verify { commandBus.dispatch(message) }
    }
}