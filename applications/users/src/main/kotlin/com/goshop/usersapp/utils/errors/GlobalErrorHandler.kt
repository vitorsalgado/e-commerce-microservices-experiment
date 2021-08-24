package com.goshop.usersapp.utils.errors

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.annotation.Order
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono

@Component
@Order(-2)
class GlobalErrorHandler(private val objectMapper: ObjectMapper) : WebExceptionHandler {
  override fun handle(exchange: ServerWebExchange, exception: Throwable): Mono<Void> {
    val bufferFactory = exchange.response.bufferFactory()
    val buffer: DataBuffer

    when (exception) {
      is DomainException -> {
        exchange.response.statusCode = HttpStatus.valueOf(exception.status)
        buffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(Error(exception.message.toString(), exception.code)))
      }
      else -> {
        exchange.response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
        buffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(Error(exception.message.toString(), "0")))
      }
    }

    exchange.response.headers.contentType = MediaType.APPLICATION_JSON

    return exchange.response.writeWith(Mono.just(buffer))
  }
}
