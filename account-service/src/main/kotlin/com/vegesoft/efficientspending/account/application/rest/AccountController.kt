package com.vegesoft.efficientspending.account.application.rest

import com.vegesoft.efficientspending.account.application.query.AccountQuery
import com.vegesoft.efficientspending.account.application.query.CurrentAccountView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/accounts")
class AccountController(
        private var accountQuery: AccountQuery
) {

    @GetMapping("/me")
    fun getAccountInformation(principal: Principal): CurrentAccountView {
        return accountQuery.findCurrentAccount(principal.name)
                .orElseThrow()
    }
}