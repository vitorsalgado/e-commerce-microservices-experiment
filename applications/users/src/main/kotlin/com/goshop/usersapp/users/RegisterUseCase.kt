package com.goshop.usersapp.users

import com.goshop.usersapp.utils.base.UseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class RegisterUseCase(private val userRepository: UserRepository) : UseCase<User, Mono<RegisterResponse>>() {
  override fun execute(args: User): Mono<RegisterResponse> {
    return userRepository.findByEmail(args.email)
      .flatMap { Mono.error<User>(UserAlreadyRegistered("User ${args.email} is already registered.")) }
      .switchIfEmpty(Mono.defer { userRepository.insert(args) })
      .map { RegisterResponse(it.id, it.email, it.name) }
  }
}
