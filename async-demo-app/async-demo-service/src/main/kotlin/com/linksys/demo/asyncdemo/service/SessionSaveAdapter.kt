package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.model.CreateTokenModel
import com.linksys.demo.asyncdemo.model.TokenModel
import java.util.concurrent.CompletableFuture

interface SessionSaveAdapter {
  /**
   * Save the sessions
   */
  fun saveSession(m: CreateTokenModel): CompletableFuture<TokenModel>

  fun saveRefresh(m: CreateTokenModel): CompletableFuture<TokenModel>
}
