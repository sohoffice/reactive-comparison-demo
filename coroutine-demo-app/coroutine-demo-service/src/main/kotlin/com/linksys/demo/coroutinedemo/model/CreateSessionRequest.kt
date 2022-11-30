package com.linksys.demo.coroutinedemo.model

import java.time.Instant
import javax.validation.constraints.NotNull

data class CreateSessionRequest(
  @NotNull
  val identity: IdentityModel,
  val createRefreshToken: Boolean = false,
  val expiryTime: Instant? = null,
  val attributes: Map<String, String>? = null
)
