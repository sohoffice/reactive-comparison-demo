package com.linksys.demo.reactivedemo.service

interface CreateSessionStrategy {
  fun sessionGenerationType(): TokenProviderType
  fun refreshGenerationType(): TokenProviderType
}
