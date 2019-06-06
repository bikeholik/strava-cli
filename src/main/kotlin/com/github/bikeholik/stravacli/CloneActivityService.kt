package com.github.bikeholik.stravacli

import io.swagger.client.api.ActivitiesApi
import io.swagger.client.model.DetailedActivity
import org.springframework.stereotype.Service
import org.threeten.bp.DayOfWeek
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.Temporal
import java.time.LocalDate
import java.util.stream.Collectors
import java.util.stream.Stream

fun Boolean.toInt() = if (this) 1 else 0

@Service
class CloneActivityService(val activitiesApi: ActivitiesApi) {
    val log = logger()

    fun clone(activityId: Long, targetDates: Stream<LocalDate>, weekdaysOnly: Boolean): List<DetailedActivity> {
        log.debug("Get activity {}", activityId)

        val activity = activitiesApi.getActivityById(activityId, false)
        log.debug("Activity to clone: {}", activity)

        return targetDates
                .map { d -> activity.startDateLocal.with { adjustWith(it as OffsetDateTime, d) } }
                .filter { !weekdaysOnly || isWeekday(it.dayOfWeek) }
                .peek { log.debug("Cloning at {}", it) }
                .map {
                    activitiesApi.createActivity(
                            activity.name,
                            activity.type.value,
                            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(it),
                            activity.movingTime,
                            activity.description + "\n -- cloned from https://www.strava.com/activities/${activity.id}",
                            activity.distance,
                            activity.isTrainer.toInt(),
                            null,
                            activity.isCommute.toInt())
                }
                .collect(Collectors.toList())
    }

    private fun isWeekday(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek.value < DayOfWeek.SATURDAY.value
    }

    private fun adjustWith(it: OffsetDateTime, date: LocalDate): Temporal? {
        return it.withYear(date.year)
                .withMonth(date.month.value)
                .withDayOfMonth(date.dayOfMonth)
    }
}
