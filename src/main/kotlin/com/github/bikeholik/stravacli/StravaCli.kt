package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.PrintHelpMessage
import com.github.ajalt.clikt.core.subcommands
import io.swagger.client.ApiClient
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

class Command(val stravaClientProperties: StravaClientProperties, val authChannel: Channel<String>, val apiClient: ApiClient) : CliktCommand(name = "strava-cli") {
    override fun run() {
        if (!isAuthorized()) {
            runBlocking {
                authorize()
            }
        }
    }

    private fun isAuthorized(): Boolean {
        return getOAuth().accessToken != null
    }

    private suspend fun authorize() {
        browse("https://www.strava.com/oauth/authorize?client_id=" +
                stravaClientProperties.clientId +
                "&response_type=code&scope=read&redirect_uri=http://localhost:8080/token")
        val token = authChannel.receive()
        getOAuth().accessToken = token
    }

    private fun getOAuth() = (apiClient.authentications.get("strava_oauth") as OAuth)

    private fun browse(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            try {
                desktop.browse(URI(url))
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        } else {
            val runtime = Runtime.getRuntime()
            try {
                runtime.exec("xdg-open $url")
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

        }
    }
}

@Component
class StravaCli(val athletesApi: AthletesApi, val authChannel: Channel<String>, val stravaClientProperties: StravaClientProperties, val commands: List<CliktCommand>) : CommandLineRunner, ApplicationContextAware {
    val log = logger()
    private var applicationContext: ConfigurableApplicationContext? = null
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext as ConfigurableApplicationContext;
    }

    override fun run(vararg args: String?) {
        val command = Command(stravaClientProperties, authChannel, athletesApi.apiClient).subcommands(commands)
        try {
            command.parse(args.toList().requireNoNulls())
        } catch (e: PrintHelpMessage) {
            log.info("{}", e.command.getFormattedHelp())
        }
        Thread.sleep(5000)
        applicationContext?.close()
    }
}
