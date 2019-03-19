package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.int
import io.swagger.client.api.AthletesApi
import org.springframework.stereotype.Component

@Component
class CloneActivityCommand(val athletesApi: AthletesApi) : CliktCommand(name = "clone"){
    val log = logger()
    val activityId by option("--activity-id").int().required()
    val dates by option("--date", "--dates").multiple()
    override fun run() {
        val athlete = athletesApi.loggedInAthlete
        log.info("Your data: {}", athlete)
        log.info("{} {}", activityId, dates)
    }
}