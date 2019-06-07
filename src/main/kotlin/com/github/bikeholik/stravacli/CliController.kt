package com.github.bikeholik.stravacli

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.time.LocalDate
import java.util.stream.Collectors
import java.util.stream.Stream

@RestController
@RequestMapping("cli")
class CliController(val objectMapper: ObjectMapper, val cloneActivityService: CloneActivityService) {

    @PostMapping("activities")
    fun clone(principal: Principal?, @RequestBody params: CloneRequest): Map<*, *> {
        logger().info("op=clone principal={} params={}", principal, params)
        val res = hashMapOf<String, Any>()
        res.putAll(map(params))
        res.putAll(mapOf("clonedActivities" to cloneActivityService.clone(params.activityId, toStream(params), true)
                .stream()
                .map { it.id }
                .collect(Collectors.toList())))
        return res;
    }

    private fun toStream(params: CloneRequest): Stream<LocalDate> {
        return CloneActivityCommand.range(params.from, params.to);
    }

    @Suppress("UNCHECKED_CAST")
    private fun map(params: Any) =
            objectMapper.convertValue(params, Map::class.java) as Map<out String, Any>

    data class CloneRequest(val activityId: Long, val from: LocalDate, val to: LocalDate)

}
