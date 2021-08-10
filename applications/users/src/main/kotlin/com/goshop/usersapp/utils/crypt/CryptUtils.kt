package com.goshop.usersapp.utils.crypt

import org.springframework.security.crypto.bcrypt.BCrypt

private const val SALT_KEY_LOG_ROUNDS = 10

fun createSalt(): String {
  return BCrypt.gensalt(SALT_KEY_LOG_ROUNDS)
}

fun createPasswordHash(password: String?, salt: String?): String {
  return BCrypt.hashpw(password, salt)
}
