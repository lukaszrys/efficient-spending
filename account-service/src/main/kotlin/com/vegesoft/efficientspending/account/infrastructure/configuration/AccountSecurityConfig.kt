package com.vegesoft.efficientspending.account.infrastructure.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@Configuration
@EnableOAuth2Sso
@EnableResourceServer
class AccountSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/accounts")
                .permitAll()
                .anyRequest()
                .authenticated()
    }

    @Bean
    fun oauth2RestTemplate(@Qualifier("oauth2ClientContext") oauth2ClientContext: OAuth2ClientContext,
                           details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate {
        return OAuth2RestTemplate(details, oauth2ClientContext)
    }
}