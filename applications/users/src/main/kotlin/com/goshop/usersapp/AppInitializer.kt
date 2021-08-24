package com.goshop.usersapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class AppInit

fun main(args: Array<String>) {
  runApplication<AppInit>(*args)
}
