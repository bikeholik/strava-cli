package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.long
import io.swagger.client.ApiException
import io.swagger.client.api.ActivitiesApi
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.Temporal
import java.time.LocalDate
import java.time.Period
import java.util.stream.LongStream
import java.util.stream.Stream

fun Boolean.toInt() = if (this) 1 else 0

@Component
class CloneActivityCommand(val activitiesApi: ActivitiesApi) : CliktCommand(name = "clone") {
    val log = logger()
    val activityId by option("--activity-id").long().required()
    val dates by option("--date").convert { it -> LocalDate.parse(it) }.multiple()
    val from by option("--from").convert { it -> LocalDate.parse(it) }
    val to by option("--to").convert { it -> LocalDate.parse(it) }
    override fun run() {
        log.debug("Get activity {}", activityId)
        try {
            val activity = activitiesApi.getActivityById(activityId, false)
            log.debug("Activity to clone: {}", activity)
            dates()
                    .map { d -> activity.startDateLocal.with { it -> adjustWith(it as OffsetDateTime, d) } }
                    .peek { d -> log.debug("Cloning at {}", d) }
                    .map { d ->
                        activitiesApi.createActivity(
                                activity.name,
                                activity.type.value,
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(d),
                                activity.elapsedTime,
                                activity.description + "\n --cloned from ${activity.id}",
                                activity.distance,
                                activity.isTrainer.toInt(),
                                null,
                                activity.isCommute.toInt())
                    }.forEach { it ->
                        log.debug("Activity cloned with id: ${it.id}")
                    }
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
            return LongStream.range(0, Period.between(from, to).days.toLong())
                    .mapToObj { i -> from?.plusDays(i) }

        }
    }

    private fun adjustWith(it: OffsetDateTime, date: LocalDate): Temporal? {
        return it.withYear(date.year)
                .withMonth(date.month.value)
                .withDayOfMonth(date.dayOfMonth)
    }
}