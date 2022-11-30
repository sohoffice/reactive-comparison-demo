package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.model.CreateSessionRequest
import com.linksys.demo.coroutinedemo.model.CreateTokenModel
import com.linksys.demo.coroutinedemo.model.SessionModel
import com.linksys.demo.coroutinedemo.model.Site
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SessionUpdateService(
  private val sessionSaveService: SessionSaveAdapter,
  private val tokenProviderFactory: TokenProviderFactory
) {

  @Transactional
  suspend fun createSession(site: Site, input: CreateSessionRequest): SessionModel = coroutineScope {
    val factoryCtx = TokenProviderFactoryContext(site.sessionGenerationType(), site.refreshGenerationType())
    val providerCtx = TokenProviderContext(site, input)

    val session = async {
      val provider = tokenProviderFactory.forSession(factoryCtx)
      val t = provider.provide(providerCtx)
      sessionSaveService.saveSession(CreateTokenModel(site, input.identity, t))
    }
    val refresh = if (input.createRefreshToken) {
      async {
        val t = tokenProviderFactory.forRefresh(factoryCtx)
          .provide(providerCtx)
        sessionSaveService.saveRefresh(CreateTokenModel(site, input.identity, t))
      }
    } else null

    SessionModel(site, session.await(), refresh?.await())
  }
}
