package com.github.bikeholik.stravacli

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

@Component
class StravaCli: CommandLineRunner  {
    val log = logger()
    override fun run(vararg args: String?) {
        log.info("Params: {}", args)
    }
}
