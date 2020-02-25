package com.vegesoft.efficientspending.account.infrastructure.repository

import com.vegesoft.efficientspending.account.domain.AuthorizationUser
import com.vegesoft.efficientspending.account.domain.repository.AuthorizationUserRepository
import com.vegesoft.efficientspending.amqp.publish.QueuePublisher
import org.springframework.stereotype.Component


@Component
class QueueAccountRepository(
        private val queuePublisher: QueuePublisher
) : AuthorizationUserRepository {

    override fun save(user: AuthorizationUser) {
        queuePublisher.publish("accountService", user)
    }
}