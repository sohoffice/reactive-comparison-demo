package com.linksys.demo.imperativedemo.model

import java.util.*

data class SessionCreatingModel(
  val site: Site,
  val identity: IdentityModel,
  val sessionToken: TokenModel,
  val refreshToken: TokenModel? = null,
  val originatedFrom: UUID? = null,
)
