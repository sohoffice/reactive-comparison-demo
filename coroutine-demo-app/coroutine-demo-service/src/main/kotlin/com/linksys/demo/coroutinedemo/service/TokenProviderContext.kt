package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.model.CreateSessionRequest
import com.linksys.demo.coroutinedemo.model.Site

data class TokenProviderContext(
  val site: Site,
  /**
   * The client side request to create a token
   */
  val input: CreateSessionRequest
)
