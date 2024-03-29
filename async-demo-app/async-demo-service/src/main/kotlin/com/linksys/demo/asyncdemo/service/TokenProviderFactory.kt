package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.model.TokenModel
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

/**
 * Provide token from the create token context
 */
interface TokenProvider {
  fun provide(ctx: TokenProviderContext): CompletableFuture<TokenModel>
}

@Component
class RandomTokenProvider : TokenProvider {
  private val random = RandomMultiplePartsGenerator(
    listOf(
      RandomPoolConfiguration(TokenCharacters.ALPHANUMERIC, 8),
      RandomPoolConfiguration(TokenCharacters.READABLE, 24)
    )
  )

  @Async
  override fun provide(ctx: TokenProviderContext): CompletableFuture<TokenModel> {
    return random.next().thenApply {
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
