package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.long
import io.swagger.client.api.ActivitiesApi
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.Temporal
import java.time.LocalDate
import java.util.stream.Stream

fun Boolean.toInt() = if (this) 1 else 0

@Component
class CloneActivityCommand(val activitiesApi: ActivitiesApi) : CliktCommand(name = "clone") {
    val log = logger()
    val activityId by option("--activity-id").long().required()
    val dates by option("--date").convert { it -> LocalDate.parse(it) }.multiple()
    override fun run() {
        log.info("Get activity {}", activityId)
        val activity = activitiesApi.getActivityById(activityId, false)
        log.info("Activity to clone: {}", activity)
        val starts = if (dates.isEmpty()) Stream.of(LocalDate.now()) else dates.stream()
        starts
                .map { d -> activity.startDateLocal.with { it -> adjustWith(it as OffsetDateTime, d) } }
                .peek { d -> log.info("Cloning at {}", d) }
                .forEach { d ->
                    activitiesApi.createActivity(
                            activity.name,
                            activity.type.name,
                            d.toString(),
                            activity.elapsedTime,
                            activity.description + "\n--cloned from ${activity.id}",
                            activity.distance,
                            activity.isTrainer.toInt(),
                            "",
                            activity.isCommute.toInt())
                }
    }

    private fun adjustWith(it: OffsetDateTime, date: LocalDate): Temporal? {
        return it.withYear(date.year)
                .withMonth(date.month.value)
                .withDayOfMonth(date.dayOfMonth)
    }
}