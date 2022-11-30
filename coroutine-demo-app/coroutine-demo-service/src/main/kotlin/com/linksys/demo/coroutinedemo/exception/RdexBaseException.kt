package com.linksys.demo.coroutinedemo.exception

import linksys.core.model.error.ErrorCodes
import org.springframework.http.HttpStatus

/**
 * The base exception of this project
 */
open class RdexBaseException(
  message: String,
  val code: ErrorCodes,
  cause: Throwable? = null,
  val status: HttpStatus? = null
) : RuntimeException(message, cause)
