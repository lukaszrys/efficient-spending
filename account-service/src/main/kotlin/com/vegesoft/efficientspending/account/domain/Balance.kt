package com.vegesoft.efficientspending.account.domain

import java.math.BigDecimal

data class Balance(
        val amount: BigDecimal = BigDecimal.ZERO
)