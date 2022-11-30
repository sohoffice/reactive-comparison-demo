package com.linksys.demo.imperativedemo.model

data class SessionModel(
  val site: Site,
  val sessionToken: TokenModel,
  val refreshToken: TokenModel? = null,
)
