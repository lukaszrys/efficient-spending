package com.vegesoft.efficientspending.balance.domain

import com.vegesoft.efficientspending.balance.domain.event.CreateBalanceEvent
import java.math.BigDecimal

class Balance(
        private val event: CreateBalanceEvent
) {
    private val id = event.id
    private val userId = event.userId
    private val currency = event.currency
    private var balance = BalanceStatus.CREATED
    private var amount = BigDecimal.ZERO

}