package com.vegesoft.efficientspending.account.application.handler

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.domain.Account
import com.vegesoft.efficientspending.account.domain.AccountRepository
import com.vegesoft.efficientspending.account.domain.AuthorizationUser
import com.vegesoft.efficientspending.account.domain.repository.AuthorizationUserRepository
import com.vegesoft.efficientspending.cqrs.CommandHandler
import org.springframework.stereotype.Service

@Service
class CreateAccountHandler(
        private val accountRepository: AccountRepository,
        private val authorizationUserRepository: AuthorizationUserRepository
) {

    @CommandHandler
    fun createAccount(createAccountCommand: CreateAccountCommand) {
        if (accountRepository.findAccountByEmail(createAccountCommand.data.email).isPresent) {
            throw RuntimeException("Account with given email already exists")
        }

        accountRepository.save(buildAccount(createAccountCommand))
        authorizationUserRepository.save(buildAuthorizationUser(createAccountCommand))
    }

    fun buildAccount(createAccountCommand: CreateAccountCommand) = Account(
            id = createAccountCommand.id,
            firstName = createAccountCommand.data.firstName,
            lastName = createAccountCommand.data.lastName,
            email = createAccountCommand.data.email)

    fun buildAuthorizationUser(createAccountCommand: CreateAccountCommand) = AuthorizationUser(
            id = createAccountCommand.id,
            username = createAccountCommand.data.email,
            password = createAccountCommand.data.password)
}