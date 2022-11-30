package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.model.TokenModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class SessionService(
  private val sessionGetAdapter: SessionGetAdapter
) {

  @Transactional
  fun getSession(token: String): Mono<TokenModel> {
    return sessionGetAdapter.get(token)
  }

}
