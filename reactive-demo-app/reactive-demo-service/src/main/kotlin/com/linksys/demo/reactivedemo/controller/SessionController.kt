package com.linksys.demo.reactivedemo.controller

import com.linksys.demo.reactivedemo.Constants
import com.linksys.demo.reactivedemo.ResourceType
import com.linksys.demo.reactivedemo.exception.RdexResourceNotFoundException
import com.linksys.demo.reactivedemo.model.CreateSessionRequest
import com.linksys.demo.reactivedemo.model.SessionModel
import com.linksys.demo.reactivedemo.model.TokenModel
import com.linksys.demo.reactivedemo.service.SessionService
import com.linksys.demo.reactivedemo.service.SessionUpdateService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/sessions")
class SessionController(
  private val sessionUpdateService: SessionUpdateService,
  private val sessionService: SessionService
) {

  @PostMapping
  fun create(@Valid @RequestBody req: CreateSessionRequest): Mono<SessionModel> {
    return sessionUpdateService.createSession(Constants.DEFAULT_SITE, req)
  }

  @GetMapping("/{token}")
  fun get(@PathVariable("token") token: String): Mono<TokenModel> {
    return sessionService.getSession(token)
      .switchIfEmpty(Mono.error(RdexResourceNotFoundException(ResourceType.SESSION_TOKEN, token)))
  }
}
