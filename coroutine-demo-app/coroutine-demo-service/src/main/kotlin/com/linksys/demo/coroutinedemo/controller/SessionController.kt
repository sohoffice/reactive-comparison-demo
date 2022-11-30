package com.linksys.demo.coroutinedemo.controller

import com.linksys.demo.coroutinedemo.Constants
import com.linksys.demo.coroutinedemo.ResourceType
import com.linksys.demo.coroutinedemo.exception.RdexResourceNotFoundException
import com.linksys.demo.coroutinedemo.model.CreateSessionRequest
import com.linksys.demo.coroutinedemo.model.SessionModel
import com.linksys.demo.coroutinedemo.model.TokenModel
import com.linksys.demo.coroutinedemo.service.SessionService
import com.linksys.demo.coroutinedemo.service.SessionUpdateService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/sessions")
class SessionController(
  private val sessionUpdateService: SessionUpdateService,
  private val sessionService: SessionService
) {

  @PostMapping
  suspend fun create(@Valid @RequestBody req: CreateSessionRequest): SessionModel {
    return sessionUpdateService.createSession(com.linksys.demo.coroutinedemo.Constants.DEFAULT_SITE, req)
  }

  @GetMapping("/{token}")
  suspend fun get(@PathVariable("token") token: String): TokenModel {
    return sessionService.getSession(token) ?: throw RdexResourceNotFoundException(ResourceType.SESSION_TOKEN, token)
  }
}
