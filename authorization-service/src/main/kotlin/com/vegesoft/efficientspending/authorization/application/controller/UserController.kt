package com.vegesoft.efficientspending.authorization.application.controller

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserRequest
import com.vegesoft.efficientspending.authorization.application.controller.mapper.UserCommandMapper
import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*


@RestController
@RequestMapping("/users")
class UserController(
        private val commandBus: CommandBus,
        private val userCommandMapper: UserCommandMapper
) {

    @GetMapping("/me")
    fun getCurrentlyLoggedUser(principal: Principal): Principal {
        return principal
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun createUser(@RequestBody createAppUserRequest: CreateAppUserRequest): IdResponse {
        val id = UUID.randomUUID();
        commandBus.dispatch(userCommandMapper.createCommand(id, createAppUserRequest))

        return IdResponse(id)
    }
}