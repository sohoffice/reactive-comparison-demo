package com.linksys.demo.imperativedemo.controller

import com.linksys.demo.imperativedemo.Constants
import com.linksys.demo.imperativedemo.ResourceType
import com.linksys.demo.imperativedemo.exception.RdexResourceNotFoundException
import com.linksys.demo.imperativedemo.model.CreateSessionRequest
import com.linksys.demo.imperativedemo.model.SessionModel
import com.linksys.demo.imperativedemo.model.TokenModel
import com.linksys.demo.imperativedemo.service.SessionService
import com.linksys.demo.imperativedemo.service.SessionUpdateService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/sessions")
class SessionController(
  private val sessionUpdateService: SessionUpdateService,
  private val sessionService: SessionService
) {

  @PostMapping
  fun create(@Valid @RequestBody req: CreateSessionRequest): SessionModel {
    return sessionUpdateService.createSession(Constants.DEFAULT_SITE, req)
  }

  @GetMapping("/{token}")
  fun get(@PathVariable("token") token: String): TokenModel {
    return sessionService.getSession(token) ?: throw RdexResourceNotFoundException(ResourceType.SESSION_TOKEN, token)
  }
}
