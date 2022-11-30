package com.linksys.demo.imperativedemo.model

import com.linksys.demo.imperativedemo.service.CreateSessionStrategy
import com.linksys.demo.imperativedemo.service.TokenProviderType
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
