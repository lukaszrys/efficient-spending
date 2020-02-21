package com.vegesoft.efficientspending.authorization.infrastructure

import com.vegesoft.efficientspending.authorization.domain.repository.AppUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class RepositoryUserDetailsService(
        val appUserRepository: AppUserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return appUserRepository.findByUsername(username)
                .map { appUser -> AppUserDetails(appUser.username, appUser.password) }
                .orElseThrow()
    }
}