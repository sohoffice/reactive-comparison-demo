package com.linksys.demo.reactivedemo.exception

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
