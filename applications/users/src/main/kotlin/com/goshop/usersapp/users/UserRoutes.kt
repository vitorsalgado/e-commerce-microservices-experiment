package com.goshop.usersapp.users

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.BodyExtractors.toMono
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.json
import java.net.URI
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/users")
@Produces(APPLICATION_JSON)
@Configuration
class UserRoutes(private val useCase: RegisterUseCase) {
  @POST
  @Consumes(APPLICATION_JSON)
  @Operation(
    summary = "User registration",
    tags = ["users"],
    responses = [
      ApiResponse(
        responseCode = "201",
        content = [Content(schema = Schema(implementation = RegisterResponse::class))]
      )
    ]
  )
  @Bean
  fun register(): RouterFunction<ServerResponse> =
    route().nest(path("/users")) { builder ->
      builder.POST { req ->
        req.body(toMono(RegisterArgs::class.java))
          .flatMap { useCase.execute(User.newUser(it.email, it.name, it.password)) }
          .flatMap { created(URI("/users/" + it.id)).json().bodyValue(it) }
      }
    }.build()
}
