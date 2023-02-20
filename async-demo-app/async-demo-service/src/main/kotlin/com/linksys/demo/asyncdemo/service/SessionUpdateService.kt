package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.Constants.TASK_EXECUTOR_SERVICE
import com.linksys.demo.asyncdemo.model.*
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CompletableFuture

@Service
class SessionUpdateService(
  private val sessionSaveService: SessionSaveAdapter,
  private val tokenProviderFactory: TokenProviderFactory
) {

  @Async(TASK_EXECUTOR_SERVICE)
  @Transactional
  fun createSession(site: Site, input: CreateSessionRequest): CompletableFuture<SessionModel> {
    val factoryCtx = TokenProviderFactoryContext(site.sessionGenerationType(), site.refreshGenerationType())
    val providerCtx = TokenProviderContext(site, input)

    val provider = tokenProviderFactory.forSession(factoryCtx)
    val fut1 = provider.provide(providerCtx)
      .thenCompose { t ->
        Thread.sleep(5000L)
        sessionSaveService.saveSession(CreateTokenModel(site, input.identity, t))
      }

    val fut2: CompletableFuture<TokenModel?> = if (input.createRefreshToken) {
      tokenProviderFactory.forRefresh(factoryCtx)
        .provide(providerCtx)
        .thenCompose { t ->
          sessionSaveService
            .saveRefresh(CreateTokenModel(site, input.identity, t))
        }
        .thenApply { it }
    } else {
      CompletableFuture.completedFuture(null)
    }

    return CompletableFuture.allOf(fut1, fut2).thenApply {
      SessionModel(site, fut1.get(), fut2.get())
    }
  }
}
