package ru.wref

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class WrefApplication

fun main(args: Array<String>) {
	runApplication<WrefApplication>(*args)
}


