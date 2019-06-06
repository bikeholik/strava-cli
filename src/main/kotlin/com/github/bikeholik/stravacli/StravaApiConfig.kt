package com.github.bikeholik.stravacli

import io.swagger.client.ApiClient
import io.swagger.client.api.ActivitiesApi
import io.swagger.client.api.AthletesApi
import org.springframework.context.annotation.*
import org.springframework.web.context.WebApplicationContext

@Configuration
class StravaApiConfig {
    @Profile("!cli")
    @Bean
    @Scope(WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun apiClient(): ApiClient {
        return ApiClient();
    }
    @Profile("cli")
    @Bean
    fun cliApiClient(): ApiClient {
        return ApiClient();
    }
    @Bean
    fun athletesApi(apiClient: ApiClient): AthletesApi {
        return AthletesApi(apiClient)
    }
    @Bean
    fun activitiesApi(apiClient: ApiClient): ActivitiesApi {
        return ActivitiesApi(apiClient)
    }
}
