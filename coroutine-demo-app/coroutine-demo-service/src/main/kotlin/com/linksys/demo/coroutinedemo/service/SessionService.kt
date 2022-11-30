package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.model.TokenModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SessionService(
  private val sessionGetAdapter: SessionGetAdapter
) {

  @Transactional
  suspend fun getSession(token: String): TokenModel? {
    return sessionGetAdapter.get(token)
  }

}
