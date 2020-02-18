package com.vegesoft.efficientspending.account.application.handler

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.domain.Account
import com.vegesoft.efficientspending.account.domain.AccountRepository
import com.vegesoft.efficientspending.cqrs.CommandHandler
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class CreateAccountHandler(
        private val accountRepository: AccountRepository,
        private val rabbitTemplate: RabbitTemplate
) {

    @CommandHandler
    fun createAccount(createAccountCommand: CreateAccountCommand) {
        val data = createAccountCommand.data
        val account = Account(
                id = createAccountCommand.id,
                firstName = data.firstName,
                lastName = data.lastName,
                email = data.email
        )

        rabbitTemplate.convertAndSend("myQueue", "Test message")
        accountRepository.save(account)
    }
}