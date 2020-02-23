package com.vegesoft.efficientspending.account.application.query

import java.util.*

interface cd ..AccountQuery {
    fun findCurrentAccount(email: String): Optional<CurrentAccountView>
}