package com.vegesoft.efficientspending.cqrs

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher

@ExtendWith(MockKExtension::class)
internal class CommandBusTest {

    @RelaxedMockK
    lateinit var publisher: ApplicationEventPublisher
    @InjectMockKs
    lateinit var tested: CommandBus

    @Test
    @DisplayName("Should dispatch command to publisher")
    fun dispatch() {
        val command = mockk<Command>()

        tested.dispatch(command)

        verify { publisher.publishEvent(command) }
    }
}