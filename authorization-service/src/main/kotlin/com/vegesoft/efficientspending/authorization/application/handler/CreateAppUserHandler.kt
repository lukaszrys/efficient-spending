package com.vegesoft.efficientspending.authorization.application.handler

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserRequest
import com.vegesoft.efficientspending.authorization.domain.Account
import com.vegesoft.efficientspending.authorization.domain.repository.AccountRepository
import com.vegesoft.efficientspending.authorization.domain.AppUser
import com.vegesoft.efficientspending.authorization.domain.repository.AppUserRepository
import com.vegesoft.efficientspending.cqrs.CommandHandler
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CreateAppUserHandler(
        val appUserRepository: AppUserRepository,
        val passwordEncoder: BCryptPasswordEncoder,
        val accountRepository: AccountRepository
) {
    @CommandHandler
    fun createAccount(createAppUserCommand: CreateAppUserCommand) {
        val appUser = mapToAppUser(createAppUserCommand)
        appUserRepository.save(appUser)

        val account = mapToAccount(createAppUserCommand.data, appUser)
        accountRepository.save(account)
    }

    private fun mapToAppUser(createAppUserCommand: CreateAppUserCommand): AppUser {
        return AppUser(id = createAppUserCommand.id,
                username = createAppUserCommand.data.email,
                password = passwordEncoder.encode(createAppUserCommand.data.password))
    }

    private fun mapToAccount(request: CreateAppUserRequest, appUser: AppUser): Account {
        return Account(id = appUser.id,
                email = appUser.username,
                firstName = request.firstName,
                lastName = request.lastName
        )
    }
}