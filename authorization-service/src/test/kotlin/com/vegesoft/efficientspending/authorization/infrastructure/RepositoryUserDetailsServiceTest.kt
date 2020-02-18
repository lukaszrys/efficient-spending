package com.vegesoft.efficientspending.authorization.infrastructure

import com.vegesoft.efficientspending.authorization.domain.AppUser
import com.vegesoft.efficientspending.authorization.domain.repository.AppUserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExtendWith(MockKExtension::class)
internal class RepositoryUserDetailsServiceTest {

    @MockK
    private lateinit var appUserRepository: AppUserRepository
    @InjectMockKs
    private lateinit var tested: RepositoryUserDetailsService

    @Test
    @DisplayName("Should load username and map to AppUserDetails")
    fun shouldLoadUsername() {
        val appUser = AppUser(UUID.randomUUID(), "username", "password")
        every { appUserRepository.findByUsername(appUser.username) } returns Optional.of(appUser)

        val result = tested.loadUserByUsername(appUser.username)

        assertEquals(result, AppUserDetails(appUser.username, appUser.password))
    }

    @Test
    @DisplayName("Should not find appuser and throw exception ")
    fun shouldNotLoadUserAndThrowException() {
        val username = "username"
        every { appUserRepository.findByUsername(username) } returns Optional.empty()

        assertFailsWith(NoSuchElementException::class) {
            tested.loadUserByUsername(username)
        }
    }
}