package com.goshop.usersapp.utils.base

abstract class UseCase<A, R> {
  abstract fun execute(args: A): R
}
