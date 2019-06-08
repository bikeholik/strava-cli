package com.github.bikeholik.stravacli

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.awt.Desktop
import java.io.IOException
import java.net.URI

@Component
class OAuthClient(val stravaClientProperties: StravaClientProperties) : ApplicationListener<ServletWebServerInitializedEvent>{
    private val logger = logger()
    private val restTemplate = RestTemplate()
    private var serverPort = 8080

    override fun onApplicationEvent(event: ServletWebServerInitializedEvent) {
        serverPort = event.webServer.port
    }

    fun exchangeToken(code: String): Pair<Map<*, *>?, Tokens> {
        logger.debug("op=token value={}", code)
        val result = restTemplate.postForObject(
                "https://www.strava.com/oauth/token?code={code}&client_secret={secret}&client_id={clientId}&grant_type=authorization_code",
                null,
                Map::class.java,
                code,
                stravaClientProperties.clientSecret,
                stravaClientProperties.clientId);
        return Pair(result, map(result))
    }

    fun refreshToken(token: String): Tokens {
        val result = restTemplate.postForObject(
                "https://www.strava.com/oauth/token?refresh_token={token}&client_secret={secret}&client_id={clientId}&grant_type=refresh_token",
                null,
                Map::class.java,
                token,
                stravaClientProperties.clientSecret,
                stravaClientProperties.clientId);
        return map(result)
    }

    fun redirectToUseBrowser() {
        redirectToUseBrowser("https://www.strava.com/oauth/authorize?client_id=" +
                stravaClientProperties.clientId +
                "&response_type=code&scope=read,activity:read,activity:write&redirect_uri=http://localhost:$serverPort/api/token")
    }

    private fun redirectToUseBrowser(url: String) {
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

    private fun map(result: Map<*, *>?): Tokens {
        logger.debug("op=result data={}", result)
        return Tokens(result?.get("access_token") as String, result["refresh_token"] as String)
    }
}
