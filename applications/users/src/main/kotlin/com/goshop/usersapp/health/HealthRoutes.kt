package com.goshop.usersapp.health

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.noContent

@Configuration
class HealthRoutes {
  @Bean
  fun health(): RouterFunction<ServerResponse> =
    RouterFunctions.route().nest(RequestPredicates.path("/")) { builder ->
      builder.GET("/health") { noContent().build() }
    }.build()
}
