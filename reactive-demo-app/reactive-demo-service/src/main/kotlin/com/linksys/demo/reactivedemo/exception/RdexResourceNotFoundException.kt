package com.linksys.demo.reactivedemo.exception

import com.linksys.demo.reactivedemo.ResourceType
import linksys.core.model.error.ErrorCodes
import org.springframework.http.HttpStatus

class RdexResourceNotFoundException(
  val resourceType: ResourceType,
  val identifier: Any
) : RdexBaseException(
  "$resourceType '$identifier' cannot be found",
  ErrorCodes.RESOURCE_NOT_FOUND,
  null,
  HttpStatus.NOT_FOUND
)
