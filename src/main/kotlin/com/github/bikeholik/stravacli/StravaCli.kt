package com.github.bikeholik.stravacli

import io.swagger.client.api.AthletesApi
import io.swagger.client.auth.OAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component
import java.awt.Desktop
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException


inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

@Component
class StravaCli(val athletesApi: AthletesApi, val authChannel: Channel<String>, val stravaClientProperties: StravaClientProperties) : CommandLineRunner, ApplicationContextAware {
    val log = logger()
    private var applicationContext: ConfigurableApplicationContext? = null
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext as ConfigurableApplicationContext;
    }

    override fun run(vararg args: String?) {
        runBlocking {
            authorize()
            val athlete = athletesApi.loggedInAthlete
            log.info("Your data: {}", athlete)
        }
        applicationContext?.close()
    }

    suspend fun authorize() {
        browse("https://www.strava.com/oauth/authorize?client_id=" +
                stravaClientProperties.clientId +
                "&response_type=code&scope=read&redirect_uri=http://localhost:8080/token")
        val token = authChannel.receive()
        (athletesApi.apiClient.authentications.get("strava_oauth") as OAuth).accessToken = token
    }

    fun browse(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            try {
                desktop.browse(URI(url))
            } catch (e: Exception) {
                // TODO Auto-generated catch block
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
