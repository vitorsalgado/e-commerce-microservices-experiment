package com.goshop.usersapp.utils

import com.goshop.usersapp.utils.cryptograph.Crypt.createPasswordHash
import com.goshop.usersapp.utils.cryptograph.Crypt.createSalt
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class CryptTest {
  @Test
  fun `it should generate a valid random salt`() {
    val salt = createSalt()
    val salt2 = createSalt()

    assertThat(salt).isNotEmpty
    assertThat(salt2).isNotEmpty
    assertNotEquals(salt, salt2)
  }

  @Test
  fun `it should generate a valid password hash from a valid plain text`() {
    val password = "super-secret"
    val salt = createSalt()

    val hashed = createPasswordHash(password, salt)

    assertEquals(hashed, createPasswordHash(password, salt))
  }
}
