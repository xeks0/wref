package ru.wref

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WrefApplication

fun main(args: Array<String>) {
	runApplication<WrefApplication>(*args)
}
