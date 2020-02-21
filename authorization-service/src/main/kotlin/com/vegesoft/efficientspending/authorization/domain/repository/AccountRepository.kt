package com.vegesoft.efficientspending.authorization.domain.repository

import com.vegesoft.efficientspending.authorization.domain.Account

interface AccountRepository {
    fun save(account: Account)
}