package com.goshop.usersapp.users

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import reactor.core.publisher.Mono
import java.util.UUID

class RegisterUseCaseTest {

  private val userRepository = mock(UserRepository::class.java)
  private val registerUseCase = RegisterUseCase(userRepository)

  @Test
  fun `it should insert new user and return it with valid uuid when email is not already registered`() {
    val email = "gohorse@org.com.br"
    val newUser = User.newUser(email, "test", "secret")

    `when`(userRepository.findByEmail(email)).thenReturn(Mono.empty())
    `when`(userRepository.insert(any(User::class.java))).then {
      Mono.just(
        User(
          UUID.randomUUID(),
          newUser.email,
          newUser.name,
          newUser.passwordHash,
          newUser.salt
        )
      )
    }

    val user = registerUseCase.execute(newUser).block()

    assertNotNull(user)
    assertDoesNotThrow { UUID.fromString(user?.id.toString()) }
    assertEquals(email, user?.email)
  }
}
