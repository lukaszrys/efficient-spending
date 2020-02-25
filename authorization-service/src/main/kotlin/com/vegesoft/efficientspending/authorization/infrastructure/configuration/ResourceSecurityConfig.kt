package com.vegesoft.efficientspending.authorization.infrastructure.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer


const val RESOURCE = "authorizationResourceApi"

@Configuration
@EnableResourceServer
class ResourceSecurityConfig : ResourceServerConfigurerAdapter() {

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId(RESOURCE).stateless(false)
    }

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/**").authenticated()
                .and().csrf().disable()
    }
}