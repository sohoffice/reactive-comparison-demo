package com.linksys.demo.reactivedemo.model

data class SessionModel(
  val site: Site,
  val sessionToken: TokenModel,
  val refreshToken: TokenModel? = null,
)
