package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.model.TokenModel
import java.util.concurrent.CompletableFuture

interface SessionGetAdapter {

  fun get(token: String): CompletableFuture<TokenModel>

}
