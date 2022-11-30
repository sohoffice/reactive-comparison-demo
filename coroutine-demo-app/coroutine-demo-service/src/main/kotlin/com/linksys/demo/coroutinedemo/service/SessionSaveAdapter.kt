package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.model.CreateTokenModel
import com.linksys.demo.coroutinedemo.model.TokenModel

interface SessionSaveAdapter {
  /**
   * Save the sessions
   */
  suspend fun saveSession(m: CreateTokenModel): TokenModel

  suspend fun saveRefresh(m: CreateTokenModel): TokenModel
}
