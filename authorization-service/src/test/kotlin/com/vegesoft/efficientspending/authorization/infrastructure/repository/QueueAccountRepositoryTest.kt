package com.vegesoft.efficientspending.authorization.infrastructure.repository

import com.vegesoft.efficientspending.amqp.publish.QueuePublisher
import com.vegesoft.efficientspending.authorization.domain.Account
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
        val account = mockk<Account>()

        tested.save(account)

        verify { queuePublisher.publish("accountService", account) }
    }
}