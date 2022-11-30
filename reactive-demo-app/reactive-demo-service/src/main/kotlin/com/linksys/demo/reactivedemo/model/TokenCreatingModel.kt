package com.linksys.demo.reactivedemo.model

import java.util.*

data class TokenCreatingModel(
  val site: Site,
  val identity: IdentityModel,
  val token: TokenModel,
  val originatedFrom: UUID? = null,
)
