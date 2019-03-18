package com.github.bikeholik.stravacli

import io.swagger.client.ApiClient
import io.swagger.client.api.AthletesApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StravaApiConfig {
    @Bean
    fun apiClient(): ApiClient {
        return ApiClient();
    }
    @Bean
    fun athletesApi(apiClient: ApiClient): AthletesApi {
        return AthletesApi(apiClient)
    }
}
