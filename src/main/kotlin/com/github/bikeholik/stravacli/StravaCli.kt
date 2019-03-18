package com.github.bikeholik.stravacli

import io.swagger.client.api.AthletesApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.awt.Desktop
import java.net.URI

inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

@Component
class StravaCli(val athletesApi: AthletesApi): CommandLineRunner  {
    val log = logger()
    override fun run(vararg args: String?) {
//        Desktop.getDesktop().browse(URI.create(""))
        log.info("Params: {}", args)
        val athlete = athletesApi.loggedInAthlete
        log.info("{}", athlete)
    }
}
