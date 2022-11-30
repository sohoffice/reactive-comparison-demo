package com.linksys.demo.coroutinedemo.model

import com.linksys.demo.coroutinedemo.service.CreateSessionStrategy
import com.linksys.demo.coroutinedemo.service.TokenProviderType
import java.util.*

data class Site(
  val id: UUID
) : CreateSessionStrategy {
  override suspend fun sessionGenerationType(): TokenProviderType {
    return TokenProviderType.RANDOM
  }

  override suspend fun refreshGenerationType(): TokenProviderType {
    return TokenProviderType.RANDOM
  }
}
