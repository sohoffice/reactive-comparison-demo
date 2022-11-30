package com.linksys.demo.imperativedemo.exception

import com.linksys.demo.imperativedemo.ResourceType
import org.springframework.http.HttpStatus

class RdexResourceNotFoundException(
  val resourceType: ResourceType,
  val identifier: Any
) : RdexBaseException(
  "$resourceType $identifier cannot be found",
  null,
  HttpStatus.NOT_FOUND
)
