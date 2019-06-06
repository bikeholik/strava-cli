package com.github.bikeholik.stravacli

import io.swagger.client.ApiClient
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor

@Profile("!cli")
@EnableResourceServer
@Configuration
class SecurityConfig(val apiClient: ApiClient) : ResourceServerConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http!!
                .requestMatchers()
                .antMatchers("/cli/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter({ request, response, chain ->
                    run {
                        MainCommand.getOAuth(apiClient).accessToken = ((SecurityContextHolder.getContext().authentication as OAuth2Authentication).details as OAuth2AuthenticationDetails).tokenValue
                        chain.doFilter(request, response)
                    }
                }, FilterSecurityInterceptor::class.java);
    }
}
