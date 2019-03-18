package com.github.bikeholik.stravacli

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class TokenController(val authChannel: Channel<String>, val stravaClientProperties: StravaClientProperties) {
    val logger = logger()
    val restTemplate = RestTemplate()

    @GetMapping("token")
    fun token(@RequestParam("code") code: String, @RequestParam("error", required = false) error: String?): Map<String, Any?> {
        logger.info("op=token value={}", code)
        val result = restTemplate.postForObject(
                "https://www.strava.com/oauth/token?code={code}&client_secret={secret}&client_id={clientId}&grant_type=authorization_code",
                null,
                Map::class.java,
                code,
                stravaClientProperties.clientSecret,
                stravaClientProperties.clientId);
        logger.debug("op=result data={}", result)
        GlobalScope.launch {
            authChannel.send(result?.get("access_token") as String)
        }
        return hashMapOf(Pair("message", "Program will continue"), Pair("authorized_user", result?.get("athlete")))
    }
}
