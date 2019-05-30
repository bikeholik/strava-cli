package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.PrintHelpMessage
import com.github.ajalt.clikt.core.subcommands
import io.swagger.client.api.AthletesApi
import kotlinx.coroutines.channels.Channel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component
import org.springframework.context.annotation.Profile


inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

@Component
@Profile("cli")
class StravaCli(val athletesApi: AthletesApi, val authChannel: Channel<Tokens>, val oAuthClient: OAuthClient, val commands: List<CliktCommand>) : CommandLineRunner, ApplicationContextAware {
    val log = logger()
    private var applicationContext: ConfigurableApplicationContext? = null
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext as ConfigurableApplicationContext;
    }

    override fun run(vararg args: String?) {
        val command = MainCommand(oAuthClient, authChannel, athletesApi.apiClient).subcommands(commands)
        try {
            command.parse(args.toList().requireNoNulls())
        } catch (e: PrintHelpMessage) {
            log.info("{}", e.command.getFormattedHelp())
        }        
        applicationContext?.close()
    }
}
