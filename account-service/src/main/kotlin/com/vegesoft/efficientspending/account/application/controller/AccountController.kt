package com.vegesoft.efficientspending.account.application.controller

import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import com.vegesoft.efficientspending.account.application.controller.mapper.AccountCommandMapper
import com.vegesoft.efficientspending.account.application.controller.response.IdResponse
import com.vegesoft.efficientspending.account.application.query.AccountQuery
import com.vegesoft.efficientspending.account.application.query.CurrentAccountView
import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@RequestMapping("/accounts")
class AccountController(
        private val accountQuery: AccountQuery,
        private val commandBus: CommandBus,
        private val mapper: AccountCommandMapper
) {

    @GetMapping("/me")
    fun getAccountInformation(principal: Principal): CurrentAccountView {
        return accountQuery.findCurrentAccount(principal.name)
                .orElseThrow()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun createUser(@RequestBody createAppUserRequest: CreateAccountRequest): IdResponse {
        val id = UUID.randomUUID();
        commandBus.dispatch(mapper.createCommand(id, createAppUserRequest))

        return IdResponse(id)
    }
}