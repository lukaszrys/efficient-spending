package com.vegesoft.efficientspending.account.domain

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface AccountRepository : MongoRepository<Account, UUID> {
    fun findAccountByEmail(email: String): Optional<Account>
}