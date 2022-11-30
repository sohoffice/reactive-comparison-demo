package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.model.CreateSessionRequest
import com.linksys.demo.reactivedemo.model.TokenCreatingModel
import com.linksys.demo.reactivedemo.model.SessionModel
import com.linksys.demo.reactivedemo.model.Site
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.util.*

@Service
class SessionUpdateService(
  private val sessionSaveService: SessionSaveAdapter,
  private val tokenProviderFactory: TokenProviderFactory
) {

  @Transactional
  fun createSession(site: Site, input: CreateSessionRequest): Mono<SessionModel> {
    val factoryCtx = TokenProviderFactoryContext(site.sessionGenerationType(), site.refreshGenerationType())
    val provider = tokenProviderFactory.forSession(factoryCtx)
    val providerCtx = TokenProviderContext(site, input)
    val sessionMono = provider.provide(providerCtx)
      .flatMap {
        sessionSaveService.saveSession(TokenCreatingModel(site, input.identity, it))
      }

    val refreshMono = if (input.createRefreshToken) {
      tokenProviderFactory.forRefresh(factoryCtx)
        .provide(providerCtx)
        .flatMap {
          sessionSaveService.saveRefresh(TokenCreatingModel(site, input.identity, it))
            .map {
              Optional.of(it)
            }
        }
    } else Mono.just(Optional.empty())

    return Mono.zip(sessionMono, refreshMono)
      .map {
        SessionModel(site, it.t1, it.t2.orElse(null))
      }
  }
}
