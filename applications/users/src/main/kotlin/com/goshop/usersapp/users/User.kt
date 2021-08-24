package com.goshop.usersapp.users

import com.goshop.usersapp.utils.cryptograph.Crypt.createPasswordHash
import com.goshop.usersapp.utils.cryptograph.Crypt.createSalt
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document
class User(
  @Id
  val id: UUID,
  val email: String,
  val name: String,
  val passwordHash: String,
  val salt: String
) {
  companion object {
    fun newUser(email: String, name: String, password: String): User {
      val salt = createSalt()
      val hashedPassword = createPasswordHash(password, salt)

      return User(UUID.randomUUID(), email, name, hashedPassword, salt)
    }
  }
}
