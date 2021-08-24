package com.goshop.usersapp.users

import com.goshop.usersapp.utils.errors.DomainException

class UserAlreadyRegistered(override val message: String) :
  DomainException(message, UserErrorCodes.ERR_USER_ALREADY_REGISTERED)
