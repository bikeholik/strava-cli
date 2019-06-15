package com.github.bikeholik.stravacli

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
class SPAErrorController : ErrorController {
    override fun getErrorPath(): String {
        return "/error"
    }

    @RequestMapping("/error", method = [RequestMethod.GET])
    fun handleError(): String {
        return "index.html"
    }
}
