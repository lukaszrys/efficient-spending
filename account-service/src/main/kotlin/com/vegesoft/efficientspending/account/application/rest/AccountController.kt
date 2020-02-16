package com.vegesoft.efficientspending.account.application.rest

import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import com.vegesoft.efficientspending.account.application.rest.mapper.AccountCommandMapper
import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/accounts")
class AccountController(
        private val commandBus: CommandBus,
        private val accountCommandMapper: AccountCommandMapper
) {

    @PostMapping
    fun createAccount(@RequestBody request: CreateAccountRequest): IdResponse {
        val id = UUID.randomUUID()
        commandBus.dispatch(accountCommandMapper.createCommand(id, request))

        return IdResponse(id)
    }
}