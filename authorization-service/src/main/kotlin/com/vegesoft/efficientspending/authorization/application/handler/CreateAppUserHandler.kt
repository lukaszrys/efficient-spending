package com.vegesoft.efficientspending.authorization.application.handler

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import com.vegesoft.efficientspending.authorization.domain.AppUser
import com.vegesoft.efficientspending.authorization.domain.AppUserRepository
import com.vegesoft.efficientspending.cqrs.CommandHandler
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CreateAppUserHandler(
        val appUserRepository: AppUserRepository,
        val passwordEncoder: BCryptPasswordEncoder
) {
    @CommandHandler
    fun createAccount(createAppUserCommand: CreateAppUserCommand) {
        val appUser = mapToAppUser(createAppUserCommand)

        appUserRepository.save(appUser)
    }

    private fun mapToAppUser(createAppUserCommand: CreateAppUserCommand): AppUser {
        return AppUser(id = createAppUserCommand.id,
                username = createAppUserCommand.username,
                password = passwordEncoder.encode(createAppUserCommand.password))
    }
}