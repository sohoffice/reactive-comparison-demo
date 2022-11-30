package com.linksys.demo.imperativedemo.service

import com.linksys.demo.imperativedemo.model.CreateSessionRequest
import com.linksys.demo.imperativedemo.model.Site

data class TokenProviderContext(
  val site: Site,
  /**
   * The client side request to create a token
   */
  val input: CreateSessionRequest
)
