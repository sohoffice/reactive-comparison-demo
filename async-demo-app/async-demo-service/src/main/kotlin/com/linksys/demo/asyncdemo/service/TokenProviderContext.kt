package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.model.CreateSessionRequest
import com.linksys.demo.asyncdemo.model.Site

data class TokenProviderContext(
  val site: Site,
  /**
   * The client side request to create a token
   */
  val input: CreateSessionRequest
)
