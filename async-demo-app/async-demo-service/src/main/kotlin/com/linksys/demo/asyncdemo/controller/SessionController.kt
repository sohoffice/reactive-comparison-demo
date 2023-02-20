package com.linksys.demo.asyncdemo.controller

import com.linksys.demo.asyncdemo.model.CreateSessionRequest
import com.linksys.demo.asyncdemo.model.SessionModel
import com.linksys.demo.asyncdemo.model.TokenModel
import com.linksys.demo.asyncdemo.service.SessionService
import com.linksys.demo.asyncdemo.service.SessionUpdateService
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/sessions")
class SessionController(
  private val sessionUpdateService: SessionUpdateService,
  private val sessionService: SessionService
) {

  @Async
  @PostMapping
  fun create(@Valid @RequestBody req: CreateSessionRequest): CompletableFuture<SessionModel> {
    return sessionUpdateService.createSession(com.linksys.demo.asyncdemo.Constants.DEFAULT_SITE, req)
  }

  @Async
  @GetMapping("/{token}")
  fun get(@PathVariable("token") token: String): CompletableFuture<TokenModel> {
    return sessionService.getSession(token)
  }
}
