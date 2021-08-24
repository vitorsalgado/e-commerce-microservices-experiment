package com.goshop.usersapp.users

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface UserRepository : ReactiveMongoRepository<User, UUID> {
  fun findByEmail(email: String): Mono<User>
}
