package com.github.bikeholik.stravacli

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class StravaCliApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(StravaCliApplication::class.java)
            .logStartupInfo(false)
            .run(*args)

    @Bean
    fun objectMapper() : ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule())
    }
}
