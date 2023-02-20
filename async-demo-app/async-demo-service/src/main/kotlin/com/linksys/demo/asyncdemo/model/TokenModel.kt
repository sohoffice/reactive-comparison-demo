package com.linksys.demo.asyncdemo.model

import java.time.Instant
import java.util.*

data class TokenModel(
  val site: Site,
  val token: String,
  val identity: UUID? = null,
  val expiryTime: Instant? = null
)
