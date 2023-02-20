package com.linksys.demo.asyncdemo.model

import com.linksys.demo.asyncdemo.service.CreateSessionStrategy
import com.linksys.demo.asyncdemo.service.TokenProviderType
import java.util.*

data class Site(
  val id: UUID
) : CreateSessionStrategy {
  override fun sessionGenerationType(): TokenProviderType {
    return TokenProviderType.RANDOM
  }

  override fun refreshGenerationType(): TokenProviderType {
    return TokenProviderType.RANDOM
  }
}
