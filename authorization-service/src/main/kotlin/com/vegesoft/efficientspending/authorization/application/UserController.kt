package com.vegesoft.efficientspending.authorization.application

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import com.vegesoft.efficientspending.cqrs.CommandBus
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpResponse
import java.security.Principal


@RestController
@RequestMapping("/users")
class UserController(
        private val commandBus: CommandBus
) {

    @GetMapping("/me")
    @PreAuthorize("#oauth2.hasScope('server')")
    fun getCurrentlyLoggedUser(principal: Principal): Principal {
        return principal
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun createUser(@RequestBody createAppUserCommand: CreateAppUserCommand) {
        commandBus.dispatch(createAppUserCommand)
    }
}