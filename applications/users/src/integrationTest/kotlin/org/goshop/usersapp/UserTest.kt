package org.goshop.usersapp

import com.goshop.usersapp.AppInit
import com.goshop.usersapp.users.RegisterArgs
import com.goshop.usersapp.users.UserErrorCodes
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  classes = [AppInit::class]
)
@ContextConfiguration(initializers = [MonDbInitializer::class])
class UserTest(@Autowired val client: WebTestClient) {

  @Test
  fun `it should register a user and return it with an id`() {
    val name = "nice name"
    val email = "tester@example.org"
    val password = "123456"

    client
      .post().uri("/users")
      .body(Mono.just(RegisterArgs(name, email, password)), RegisterArgs::class.java)
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("$.id").isNotEmpty
      .jsonPath("$.name").isEqualTo(name)
      .jsonPath("$.email").isEqualTo(email)
  }

  @Test
  @DisplayName("it should return 400 (Bad Request) when user email is already registered")
  fun testUserAlreadyRegistered() {
    val name = "very nice name"
    val email = "tester-registered@example.org"
    val password = "123456"

    client
      .post().uri("/users")
      .body(Mono.just(RegisterArgs(name, email, password)), RegisterArgs::class.java)
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isCreated

    client
      .post().uri("/users")
      .body(Mono.just(RegisterArgs(name, email, password)), RegisterArgs::class.java)
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.code").isEqualTo(UserErrorCodes.ERR_USER_ALREADY_REGISTERED)
  }
}
