package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.model.TokenModel
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

/**
 * Provide token from the create token context
 */
interface TokenProvider {
  suspend fun provide(ctx: TokenProviderContext): TokenModel
}

@Component
class RandomTokenProvider : TokenProvider {
  private val random = RandomMultiplePartsGenerator(
    listOf(
      RandomPoolConfiguration(TokenCharacters.ALPHANUMERIC, 8),
      RandomPoolConfiguration(TokenCharacters.READABLE, 24)
    )
  )

  override suspend fun provide(ctx: TokenProviderContext): TokenModel {
    return TokenModel(ctx.site, random.next(), ctx.input.identity.id, ctx.input.expiryTime)
  }
}

data class TokenProviderFactoryContext(
  val sessionType: TokenProviderType,
  val refreshType: TokenProviderType,
)

@Service
class TokenProviderFactory(
  private val random: RandomTokenProvider
) {

  fun forSession(ctx: TokenProviderFactoryContext): TokenProvider {
    when (ctx.sessionType) {
      TokenProviderType.RANDOM -> return random
      else -> TODO("Not yet implemented")
    }
  }

  fun forRefresh(ctx: TokenProviderFactoryContext): TokenProvider {
    when (ctx.refreshType) {
      TokenProviderType.RANDOM -> return random
      else -> TODO("Not yet implemented")
    }
  }
}
