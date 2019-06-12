package com.github.bikeholik.stravacli

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.client.api.AthletesApi
import io.swagger.client.model.DetailedAthlete
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.LocalDate
import java.util.stream.Collectors
import java.util.stream.Stream

@RestController
@RequestMapping("cli")
class CliController(val objectMapper: ObjectMapper, val cloneActivityService: CloneActivityService, val athletesApi: AthletesApi) {

    @GetMapping("account")
    fun getAthlete(): Map<String, DetailedAthlete> {
        return mapOf("athlete" to athletesApi.loggedInAthlete)
    }

    @PostMapping("activities")
    fun clone(principal: Principal?, @RequestBody params: CloneRequest): Map<*, *> {
        logger().info("op=clone principal={} params={}", principal, params)
        val res = hashMapOf<String, Any>()
        res.putAll(map(params))
        val clonedActivities = cloneActivityService.clone(params.activityId, toStream(params), true)
        res.putAll(mapOf("clonedActivity" to mapOf("id" to clonedActivities.first.id, "name" to clonedActivities.first.name)))
        res.putAll(mapOf("clonedActivities" to clonedActivities
                .second
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
