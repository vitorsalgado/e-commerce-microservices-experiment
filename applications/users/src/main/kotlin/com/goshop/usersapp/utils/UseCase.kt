package com.goshop.usersapp.utils

abstract class UseCase<A, R> {
  abstract fun execute(args: A): R
}
