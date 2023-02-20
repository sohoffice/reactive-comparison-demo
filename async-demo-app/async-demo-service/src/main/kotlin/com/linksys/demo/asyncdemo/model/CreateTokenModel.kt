package com.linksys.demo.asyncdemo.model

import java.util.*

data class CreateTokenModel(
  val site: Site,
  val identity: IdentityModel,
  val token: TokenModel,
  val originatedFrom: UUID? = null,
)
