package com.goshop.usersapp.utils

abstract class NoResponseUseCase<A> {
  abstract fun execute(args: A)
}
