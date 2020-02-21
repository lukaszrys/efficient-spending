package com.vegesoft.efficientspending.authorization.domain.repository

import com.vegesoft.efficientspending.authorization.domain.AppUser
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppUserRepository : MongoRepository<AppUser, UUID> {
    fun findByUsername(username: String): Optional<AppUser>
}