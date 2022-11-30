package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.model.TokenModel
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Provide token from the create token context
 */
interface TokenProvider {
  fun provide(ctx: TokenProviderContext): Mono<TokenModel>
}

@Component
class RandomTokenProvider : TokenProvider {
  private val random = RandomMultiplePartsGenerator(
    listOf(
      RandomPoolConfiguration(TokenCharacters.ALPHANUMERIC, 8),
      RandomPoolConfiguration(TokenCharacters.READABLE, 24)
    )
  )

  override fun provide(ctx: TokenProviderContext): Mono<TokenModel> {
    return random.next().map {
      TokenModel(ctx.site, it, ctx.input.identity.id, ctx.input.expiryTime)
    }
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
