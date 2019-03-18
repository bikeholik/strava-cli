package com.github.bikeholik.stravacli

import io.swagger.client.api.AthletesApi
import io.swagger.client.auth.OAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.awt.Desktop
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException


inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

@Component
class StravaCli(val athletesApi: AthletesApi, val authChannel: Channel<String>, val stravaClientProperties: StravaClientProperties) : CommandLineRunner {
    val log = logger()
    override fun run(vararg args: String?) {
        browse("https://www.strava.com/oauth/authorize?client_id=" +
                stravaClientProperties.clientId +
                "&response_type=code&scope=read&redirect_uri=http://localhost:8080/token")
        runBlocking {
            authorize()
            val athlete = athletesApi.loggedInAthlete
            log.info("Your data: {}", athlete)
        }
        System.exit(0)
    }

    suspend fun authorize() {
        val token = authChannel.receive()
        (athletesApi.apiClient.authentications.get("strava_oauth") as OAuth).accessToken = token
    }

    fun browse(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            try {
                desktop.browse(URI(url))
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }

        } else {
            val runtime = Runtime.getRuntime()
            try {
                runtime.exec("xdg-open $url")
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }
    }
}
