package com.linksys.demo.imperativedemo.model

import java.time.Instant

data class TokenModel(
  val site: Site,
  val token: String,
  val identity: IdentityModel? = null,
  val expiryTime: Instant? = null
)
