package com.vegesoft.efficientspending.account.application.query

import java.util.*

interface AccountQuery {
    fun findCurrentAccount(email: String): Optional<CurrentAccountView>
}