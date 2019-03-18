package com.github.bikeholik.stravacli

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "strava")
class StravaClientProperties {
    lateinit var clientId: String
    lateinit var clientSecret: String
}
