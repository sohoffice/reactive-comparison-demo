package com.linksys.demo.imperativedemo.service

import com.linksys.demo.imperativedemo.model.CreateSessionRequest
import com.linksys.demo.imperativedemo.model.SessionCreatingModel
import com.linksys.demo.imperativedemo.model.SessionModel
import com.linksys.demo.imperativedemo.model.Site
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SessionUpdateService(
  private val sessionSaveService: SessionSaveAdapter,
  private val tokenProviderFactory: TokenProviderFactory
) {

  @Transactional
  fun createSession(site: Site, input: CreateSessionRequest): SessionModel {
    val factoryCtx = TokenProviderFactoryContext(site.sessionGenerationType(), site.refreshGenerationType())
    val provider = tokenProviderFactory.forSession(factoryCtx)
    val providerCtx = TokenProviderContext(site, input)

    val session = provider.provide(providerCtx)
    val refresh = if (input.createRefreshToken) {
      tokenProviderFactory.forRefresh(factoryCtx)
        .provide(providerCtx)
    } else null
    val m = SessionCreatingModel(site, input.identity, session, refresh, null)
    return sessionSaveService.save(m)
  }
}
