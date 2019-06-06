package com.github.bikeholik.stravacli

import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api")
class TestController {

    @GetMapping("profile")
    fun getProfile(principal : Principal?, @RequestParam params: Map<String, String>): Map<*, *> {
        logger().info("op=getProfile principal={} params={}", principal, params)
        return mapOf("now" to System.currentTimeMillis(), "principal" to (principal != null));
    }

    @PostMapping("profile")
    fun searchProfile(principal : Principal?, @RequestParam params: Map<String, String>): Map<*, *> {
        logger().info("op=searchProfile principal={} params={}", principal, params)
        val res = hashMapOf("now" to System.currentTimeMillis(), "principal" to (principal != null));
        res.putAll(params)
        return res;
    }

    @PostMapping("profile2")
    fun searchProfile2(principal : Principal?, @RequestBody params: Map<String, String>): Map<*, *> {
        logger().info("op=searchProfile principal={} params={}", principal, params)
        val res = hashMapOf("now" to System.currentTimeMillis(), "principal" to (principal != null));
        res.putAll(params)
        return res;
    }
}
