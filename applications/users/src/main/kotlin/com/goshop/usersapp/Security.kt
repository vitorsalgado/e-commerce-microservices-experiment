package com.goshop.usersapp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class Security {
  @Bean
  fun springSecurityFilterChain(httpSecurity: ServerHttpSecurity): SecurityWebFilterChain {
    return httpSecurity
      .csrf { csrf -> csrf.disable() }.build()
  }
}
