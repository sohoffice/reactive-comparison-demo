package com.linksys.demo.imperativedemo.service

interface CreateSessionStrategy {
  fun sessionGenerationType(): TokenProviderType
  fun refreshGenerationType(): TokenProviderType
}
