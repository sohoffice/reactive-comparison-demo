package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.model.TokenCreatingModel
import com.linksys.demo.reactivedemo.model.SessionModel
import com.linksys.demo.reactivedemo.model.TokenModel
import reactor.core.publisher.Mono

interface SessionSaveAdapter {
  /**
   * Save the sessions
   */
  fun saveSession(m: TokenCreatingModel): Mono<TokenModel>

  fun saveRefresh(m: TokenCreatingModel): Mono<TokenModel>
}
