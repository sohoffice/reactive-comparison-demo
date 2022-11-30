package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.model.TokenModel

interface SessionGetAdapter {

  suspend fun get(token: String): TokenModel?

}
