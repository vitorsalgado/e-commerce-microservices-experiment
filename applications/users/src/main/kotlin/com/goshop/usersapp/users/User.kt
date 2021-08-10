package com.goshop.usersapp.users

import java.time.LocalDate
import java.util.UUID

class User {
  val id: String
  val email: String
  val name: String
  val birth: LocalDate?
  val addresses: List<Address>

  constructor(email: String, name: String, passwordHash: String) {
    this.id = UUID.randomUUID().toString()
    this.email = email
    this.name = name
    this.birth = LocalDate.now()
    this.addresses = emptyList()
  }
}
