package com.github.bikeholik.stravacli

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.security.Principal

@RestController
@RequestMapping("api")
class TokenController(val authChannel: Channel<Tokens>, val oAuthClient: OAuthClient) {

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

    @GetMapping("profile")
    fun getProfile(principal : Principal?): Map<*, *> {
        logger().info("op=getProfile principal={}", principal)
        return mapOf("now" to System.currentTimeMillis(), "principal" to (principal != null));
    }
}
