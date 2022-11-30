package com.linksys.demo.coroutinedemo.model

data class SessionModel(
  val site: Site,
  val sessionToken: TokenModel,
  val refreshToken: TokenModel? = null,
)
