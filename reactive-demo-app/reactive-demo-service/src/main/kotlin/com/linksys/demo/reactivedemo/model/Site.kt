package com.linksys.demo.reactivedemo.model

import com.linksys.demo.reactivedemo.service.CreateSessionStrategy
import com.linksys.demo.reactivedemo.service.TokenProviderType
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
