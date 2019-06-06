package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.long
import io.swagger.client.ApiException
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period
import java.util.stream.LongStream
import java.util.stream.Stream

@Component
class CloneActivityCommand(val activitiesApi: CloneActivityService) : CliktCommand(name = "clone") {
    val log = logger()
    val activityId by option("--activity-id").long().required()
    val dates by option("--date").convert { LocalDate.parse(it) }.multiple()
    val from by option("--from").convert { LocalDate.parse(it) }
    val to by option("--to").convert { LocalDate.parse(it) }
    val weekdaysOnly by option("--weekdays-only").flag()
    override fun run() {
        try {
            activitiesApi.clone(activityId, dates(), weekdaysOnly)
                    .stream()
                    .forEach { log.debug("Activity cloned with id: ${it.id}") }
        } catch (e: ApiException) {
            log.error("Failure cloning activity {}", activityId, e)
        }
    }

    private fun dates(): Stream<LocalDate> {
        if (dates.isEmpty() && (from == null || to == null || from?.isAfter(to) == true)) {
            return Stream.of(LocalDate.now())
        } else if (!dates.isEmpty()) {
            return dates.stream()
        } else {
            return range(from, to)
        }
    }

    companion object {
        fun range(from: LocalDate?, to: LocalDate?): Stream<LocalDate> =
                LongStream.rangeClosed(0, Period.between(from, to).days.toLong())
                        .mapToObj { i -> from?.plusDays(i) }
    }
}
