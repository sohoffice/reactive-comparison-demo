package com.linksys.demo.imperativedemo.service

import com.linksys.demo.imperativedemo.model.TokenModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SessionService(
  private val sessionGetAdapter: SessionGetAdapter
) {

  @Transactional
  fun getSession(token: String): TokenModel? {
    return sessionGetAdapter.get(token)
  }

}
