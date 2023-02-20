package com.linksys.demo.asyncdemo.model

data class SessionModel(
  val site: Site,
  val sessionToken: TokenModel,
  val refreshToken: TokenModel? = null,
)
