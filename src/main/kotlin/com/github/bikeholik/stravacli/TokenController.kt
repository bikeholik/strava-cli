package com.github.bikeholik.stravacli

import io.swagger.client.api.AthletesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.HashMap

@RestController
@RequestMapping("api")
class TokenController(val authChannel: Channel<Tokens>, val oAuthClient: OAuthClient, val athletesApi: AthletesApi) {

    @GetMapping("token")
    fun token(@RequestParam("code") code: String, @RequestParam("error", required = false) error: String?): ModelAndView {
        val result = oAuthClient.exchangeToken(code)
        GlobalScope.launch {
            authChannel.send(result.second)
        }
        return ModelAndView("post-auth", hashMapOf(Pair("message", "Program will continue"), Pair("authorized_user", result.first?.get("athlete"))))
    }

    @PostMapping("authorize")
    fun authenticate(@RequestParam("code") code: String): Map<*, *>? {
        val result = oAuthClient.exchangeToken(code)
        return result.first
    }

    @PostMapping("refresh")
    fun refresh(@RequestParam("refresh_token") token: String): Map<*, *>? {
        val result = oAuthClient.refreshToken(token)
        MainCommand.getOAuth(athletesApi.apiClient).accessToken = result.second.accessToken
        val res = hashMapOf<String, Any>()
        res.putAll(rawResult(result))
        res["athlete"] = athletesApi.loggedInAthlete
        return result.first
    }

    @Suppress("UNCHECKED_CAST")
    private fun rawResult(result: Pair<Map<*, *>?, Tokens>) =
            (result.first!! as Map<out String, Any>)
}
