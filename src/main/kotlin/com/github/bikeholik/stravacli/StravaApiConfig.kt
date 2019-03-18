package com.github.bikeholik.stravacli

import io.swagger.client.ApiClient
import io.swagger.client.api.AthletesApi
import io.swagger.client.auth.OAuth
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StravaApiConfig {
    @Bean
    fun apiClient(): ApiClient {
        val apiClient = ApiClient()
        (apiClient.authentications.get("strava_oauth") as OAuth).accessToken = "769784fe441611e23801fec422767c3b0daf714f"
        return apiClient;
    }
    @Bean
    fun athletesApi(apiClient: ApiClient): AthletesApi {
        return AthletesApi(apiClient)
    }
}
