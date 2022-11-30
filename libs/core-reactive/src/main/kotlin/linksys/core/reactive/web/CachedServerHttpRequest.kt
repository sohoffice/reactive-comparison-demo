package linksys.core.reactive.web

import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpRequestDecorator
import reactor.core.publisher.Flux
import java.io.ByteArrayOutputStream

class CachedServerHttpRequest(req: ServerHttpRequest) : ServerHttpRequestDecorator(req) {
  private val cache = ByteArrayOutputStream()

  override fun getBody(): Flux<DataBuffer> {
    return super.getBody()
      .doOnNext(this::capture)
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