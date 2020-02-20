package com.vegesoft.efficientspending.account.application.handler

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.domain.Account
import com.vegesoft.efficientspending.account.domain.AccountRepository
import com.vegesoft.efficientspending.cqrs.CommandHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class CreateAccountHandler(
        private val accountRepository: AccountRepository
) {

    @CommandHandler
    fun createAccount(createAccountCommand: CreateAccountCommand) {
        val account = Account(
                id = createAccountCommand.id,
                firstName = createAccountCommand.firstName,
                lastName = createAccountCommand.lastName,
                email = createAccountCommand.email
        )

        accountRepository.save(account)
    }
}