package com.goshop.usersapp.users.usecases.registration

import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.BodyExtractors.toMono
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.json

@Bean
fun register(useCase: UseCase): RouterFunction<ServerResponse> =
  route(POST("/users")) { req ->
    req.body(toMono(Args::class.java))
      .doOnNext(useCase::execute)
      .then(ok().json().build())
  }
