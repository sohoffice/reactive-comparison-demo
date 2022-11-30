package com.linksys.demo.imperativedemo.exception

import org.springframework.http.HttpStatus

/**
 * The base exception of this project
 */
open class RdexBaseException(
  message: String,
  cause: Throwable? = null,
  val status: HttpStatus? = null
) : RuntimeException(message, cause)
