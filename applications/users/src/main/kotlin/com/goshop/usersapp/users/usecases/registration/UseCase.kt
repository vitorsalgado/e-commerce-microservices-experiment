package com.goshop.usersapp.users.usecases.registration

import com.goshop.usersapp.users.User
import com.goshop.usersapp.users.UserRepository
import com.goshop.usersapp.utils.UseCase
import com.goshop.usersapp.utils.crypt.createPasswordHash
import com.goshop.usersapp.utils.crypt.createSalt
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class UseCase(private val userRepository: UserRepository) : UseCase<Args, Mono<User>>() {
  override fun execute(args: Args): Mono<User> {
    userRepository.findByEmail(args.email)
      .doOnNext { print(it) }
      .switchIfEmpty {
        val salt = createSalt()
        val hashedPassword = createPasswordHash(args.password, salt)
        val user = User(args.email, args.name, hashedPassword)

        userRepository.insert(user)
      }
      .toMono()
  }
}
