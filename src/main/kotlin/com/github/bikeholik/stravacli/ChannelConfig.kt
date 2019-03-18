package com.github.bikeholik.stravacli

import org.springframework.context.annotation.Configuration
import kotlinx.coroutines.channels.Channel
import org.springframework.context.annotation.Bean

@Configuration
class ChannelConfig {
    @Bean
    fun authChannel(): Channel<String> {
        return Channel(2)
    }
}
