package linksys.core.reactive.web

import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.ServerWebExchangeDecorator

class CachedServerWebExchange(delegate: ServerWebExchange) : ServerWebExchangeDecorator(delegate) {
  val requestCache = CachedServerHttpRequest(delegate.request)
  val responseCache = CachedServerHttpResponse(delegate.response)

  override fun getRequest(): ServerHttpRequest {
    return requestCache
  }

  override fun getResponse(): ServerHttpResponse {
    return responseCache
  }

}