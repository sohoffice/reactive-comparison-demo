package com.linksys.demo.reactivedemo.web

import com.linksys.demo.reactivedemo.exception.RdexBaseException
import linksys.core.model.error.ErrorModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandlerControllerAdvise {

  @ExceptionHandler(RdexBaseException::class)
  fun handleRdexBaseException(ex: RdexBaseException): ResponseEntity<Any> {
    val status = ex.status ?: HttpStatus.INTERNAL_SERVER_ERROR
    return ResponseEntity.status(status)
      .body(ErrorModel(ex.code, ex.message))
  }
}
