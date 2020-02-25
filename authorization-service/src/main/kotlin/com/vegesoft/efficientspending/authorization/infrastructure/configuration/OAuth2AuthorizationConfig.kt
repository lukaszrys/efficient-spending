package com.vegesoft.efficientspending.authorization.infrastructure.configuration

import com.vegesoft.efficientspending.authorization.infrastructure.RepositoryUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore

@Configuration
@EnableAuthorizationServer
class OAuth2AuthorizationConfig(
        val userDetailsService: RepositoryUserDetailsService,
        val authenticationManager: AuthenticationManager,
        val passwordEncoder: BCryptPasswordEncoder
) : AuthorizationServerConfigurerAdapter() {

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient("web-app")
                .secret(passwordEncoder.encode("web-app-secret"))
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("ui")
                .autoApprove(true)
                .and()
                .withClient("account-service")
                .secret(passwordEncoder.encode("account-service"))
                .authorizedGrantTypes("authorization_code")
                .scopes("service")
                .autoApprove(true)
                .and()
                .withClient("balance-service")
                .secret(passwordEncoder.encode("balance-service"))
                .authorizedGrantTypes("authorization_code")
                .scopes("service")
                .autoApprove(true)
    }

    @Bean
    fun tokenStore(): TokenStore {
        return InMemoryTokenStore()
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }
}