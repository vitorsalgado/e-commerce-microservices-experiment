package com.goshop.usersapp.utils.errors

import org.springframework.http.HttpStatus

open class DomainException(message: String, val code: String, val status: Int = HttpStatus.BAD_REQUEST.value()) : RuntimeException(message)
