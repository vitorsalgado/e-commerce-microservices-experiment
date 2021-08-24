package com.goshop.usersapp.users

import java.util.UUID

data class RegisterResponse(
  val id: UUID,
  val email: String,
  val name: String,
)
