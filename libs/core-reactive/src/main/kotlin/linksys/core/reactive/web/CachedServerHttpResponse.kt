package linksys.core.reactive.web

import org.reactivestreams.Publisher
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.http.server.reactive.ServerHttpResponseDecorator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.ByteArrayOutputStream

class CachedServerHttpResponse(res: ServerHttpResponse) : ServerHttpResponseDecorator(res) {
  private val cache = ByteArrayOutputStream()
  override fun writeWith(body: Publisher<out DataBuffer>): Mono<Void> {
    val buffer = Flux.from(body)
    return super.writeWith(buffer.doOnNext(this::capture))
  }

  fun getContent(): ByteArray? {
    if (cache.size() <= 0) {
      return null
    }
    return cache.toByteArray().copyOf(cache.size())
  }

  private fun capture(buf: DataBuffer) {
    val bb = buf.asByteBuffer()
    if(bb.hasArray()) {
      cache.write(bb.array().sliceArray(0 .. bb.remaining()))
    }
  }

}