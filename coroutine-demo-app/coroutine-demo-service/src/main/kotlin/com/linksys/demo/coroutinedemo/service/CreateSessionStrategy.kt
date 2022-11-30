package com.linksys.demo.coroutinedemo.service

interface CreateSessionStrategy {
  suspend fun sessionGenerationType(): TokenProviderType
  suspend fun refreshGenerationType(): TokenProviderType
}
