package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.model.TokenModel
import reactor.core.publisher.Mono

interface SessionGetAdapter {

  fun get(token: String): Mono<TokenModel>

}
