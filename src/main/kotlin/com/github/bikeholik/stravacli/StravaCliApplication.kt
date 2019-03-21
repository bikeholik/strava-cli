package com.github.bikeholik.stravacli

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class StravaCliApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(StravaCliApplication::class.java)
            .logStartupInfo(false)
            .run(*args)
}
