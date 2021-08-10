package com.goshop.usersapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppInit

fun main(args: Array<String>) {
	runApplication<AppInit>(*args)
}
