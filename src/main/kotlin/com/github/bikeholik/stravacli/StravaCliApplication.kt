package com.github.bikeholik.stravacli

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StravaCliApplication

fun main(args: Array<String>) {
	runApplication<StravaCliApplication>(*args)
}
