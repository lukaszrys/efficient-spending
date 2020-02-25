package com.vegesoft.efficientspending.account.infrastructure.repository

import com.vegesoft.efficientspending.account.domain.AuthorizationUser
import com.vegesoft.efficientspending.amqp.publish.QueuePublisher
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class QueueAccountRepositoryTest {
    @RelaxedMockK
    private lateinit var queuePublisher: QueuePublisher
    @InjectMockKs
    private lateinit var tested: QueueAccountRepository

    @Test
    fun shouldPublishTheMessage() {
        val user = mockk<AuthorizationUser>()

        tested.save(user)

        verify { queuePublisher.publish("accountService", user) }
    }
}