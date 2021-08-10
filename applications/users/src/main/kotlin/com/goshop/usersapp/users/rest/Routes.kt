package com.goshop.usersapp.users.rest

import com.goshop.usersapp.users.usecases.registration.UseCase
import org.springframework.stereotype.Component

@Component
class Routes(val useCase: UseCase) {

}
