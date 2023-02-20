package com.linksys.demo.asyncdemo.service

interface CreateSessionStrategy {
  fun sessionGenerationType(): TokenProviderType
  fun refreshGenerationType(): TokenProviderType
}
