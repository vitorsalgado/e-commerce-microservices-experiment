package com.goshop.usersapp.utils.mongodb

import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate

@Configuration
class ReactiveConfig(val mongoClient: MongoClient) {
  @Bean
  fun reactiveMongoTemplate(): ReactiveMongoTemplate? {
    return ReactiveMongoTemplate(mongoClient, "test")
  }
}
