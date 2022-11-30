package linksys.core.reactive.web

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.reactive.HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.ServerWebExchange.LOG_ID_ATTRIBUTE
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import org.springframework.web.util.pattern.PathPattern
import reactor.core.publisher.Mono
import java.net.URI
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

class RequestLoggingWebFilter : WebFilter {
  private val logger = LoggerFactory.getLogger("request.detail")

  var logHeaders = false
    set(value) {
      field = value
    }

  override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
    val begin = System.currentTimeMillis()
    val logId = exchange.getAttribute<String>(LOG_ID_ATTRIBUTE)
    MDC.put("traceId", logId)

    return if (logger.isDebugEnabled) {
      val exchange2 = CachedServerWebExchange(exchange)
      chain.filter(exchange2)
        .doAfterTerminate {
          val end = System.currentTimeMillis()
          val elapsed = end - begin
          val req = exchange2.requestCache
          logDetail("REQ", exchange2, elapsed, req.method, req.uri, req.headers, req.getContent())
          val res = exchange2.responseCache
          logDetail("RES", exchange2, elapsed, req.method, req.uri, res.headers, res.getContent())
        }
    } else {
      chain.filter(exchange)
    }
  }

  /**
   * Log the below information
   *
   * 1. Method, URI
   * 2. Canonical path, aka path pattern in spring
   * 3. Elapsed time
   * 3. Headers if logHeaders=true
   * 4. Body
   */
  private fun logDetail(
    tag: String,
    exchange: ServerWebExchange,
    elapsed: Long,
    method: HttpMethod?,
    uri: URI,
    headers: HttpHeaders?,
    content: ByteArray?
  ) {
    val pattern = exchange.getAttribute<PathPattern>(BEST_MATCHING_PATTERN_ATTRIBUTE)
    val headers2 = if (logHeaders) {
      headers?.map {
        "[${it.key}:${it.value.joinToString(",")}]"
      }?.joinToString("") ?: ""
    } else ""
    val body = if (content != null) {
      StandardCharsets.UTF_8.decode(ByteBuffer.wrap(content)).toString()
    } else {
      ""
    }

    logger.debug("[{}:{} {}][CP:{} {}][T:{}]{}[[{}]]", tag, method, uri, method, pattern, elapsed, headers2, body)
  }
}