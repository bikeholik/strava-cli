package com.github.bikeholik.stravacli

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class TokenController(val authChannel: Channel<Tokens>, val oAuthClient: OAuthClient) {

    @GetMapping("token")
    fun token(@RequestParam("code") code: String, @RequestParam("error", required = false) error: String?): ModelAndView {
        val result = oAuthClient.exchangeToken(code)
        GlobalScope.launch {
            authChannel.send(result.second)
        }
        return ModelAndView("index", hashMapOf(Pair("message", "Program will continue"), Pair("authorized_user", result.first?.get("athlete"))))
    }
}
