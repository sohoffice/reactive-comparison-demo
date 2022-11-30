package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.model.CreateSessionRequest
import com.linksys.demo.reactivedemo.model.Site

data class TokenProviderContext(
  val site: Site,
  /**
   * The client side request to create a token
   */
  val input: CreateSessionRequest
)
