package com.vegesoft.efficientspending.authorization.infrastructure

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class RepositoryUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return AppUserDetails("username", BCryptPasswordEncoder().encode("password"))
    }
}