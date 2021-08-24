package org.goshop.usersapp

import com.goshop.usersapp.AppInit
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  classes = [AppInit::class]
)
class HealthTest(@Autowired val client: WebTestClient) {
  @Test
  fun `it should return success with status 204 (No Content) when server is online`() {
    client
      .get().uri("/health")
      .exchange()
      .expectStatus().isNoContent
  }
}
