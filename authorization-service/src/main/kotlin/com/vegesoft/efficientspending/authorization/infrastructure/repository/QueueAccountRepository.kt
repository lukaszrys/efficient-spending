package com.vegesoft.efficientspending.authorization.infrastructure.repository

import com.vegesoft.efficientspending.amqp.QueuePublisher
import com.vegesoft.efficientspending.authorization.domain.Account
import com.vegesoft.efficientspending.authorization.domain.repository.AccountRepository
import org.springframework.stereotype.Component


@Component
class QueueAccountRepository(
        private val queuePublisher: QueuePublisher
) : AccountRepository {
    
    override fun save(account: Account) {
        queuePublisher.publish("accountService", account)
    }
}