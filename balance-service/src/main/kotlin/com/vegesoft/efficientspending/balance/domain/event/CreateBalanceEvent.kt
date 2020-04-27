package com.vegesoft.efficientspending.balance.domain.event

import java.util.*

data class CreateBalanceEvent(
        val id: UUID,
        val userId: UUID,
        val currency: String
)