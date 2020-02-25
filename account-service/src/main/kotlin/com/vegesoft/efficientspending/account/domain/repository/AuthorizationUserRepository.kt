package com.vegesoft.efficientspending.account.domain.repository

import com.vegesoft.efficientspending.account.domain.AuthorizationUser

interface AuthorizationUserRepository {
    fun save(user: AuthorizationUser)
}