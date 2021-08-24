package com.goshop.usersapp.utils.cryptograph

import org.springframework.security.crypto.bcrypt.BCrypt

object Crypt {
  private const val SALT_KEY_LOG_ROUNDS = 10

  fun createSalt(): String = BCrypt.gensalt(SALT_KEY_LOG_ROUNDS)

  fun createPasswordHash(password: String, salt: String): String = BCrypt.hashpw(password, salt)
}
