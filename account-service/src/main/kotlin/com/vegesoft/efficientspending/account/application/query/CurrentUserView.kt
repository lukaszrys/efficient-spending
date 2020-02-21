package com.vegesoft.efficientspending.account.application.query

import java.math.BigDecimal

data class CurrentUserView(
        val firstName: String,
        val lastName: String,
        val email: String,
        val balance: BigDecimal
)