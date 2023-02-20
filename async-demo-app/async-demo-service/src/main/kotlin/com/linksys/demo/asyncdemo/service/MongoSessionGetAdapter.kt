package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.ResourceType
import com.linksys.demo.asyncdemo.converter.toModel
import com.linksys.demo.asyncdemo.exception.RdexResourceNotFoundException
import com.linksys.demo.asyncdemo.model.TokenModel
import com.linksys.demo.asyncdemo.repository.MongoSessionRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CompletableFuture

@Service
@ConditionalOnProperty("app.persistence", havingValue = "mongo")
class MongoSessionGetAdapter(
  private val sessionRepository: MongoSessionRepository
) : SessionGetAdapter {

  @Async
  @Transactional
  override fun get(token: String): CompletableFuture<TokenModel> {
    return sessionRepository.findByToken(token)
      .thenCompose {
        if (it == null) {
          CompletableFuture.failedFuture(RdexResourceNotFoundException(ResourceType.SESSION_TOKEN, token))
        } else {
          CompletableFuture.completedFuture(it.toModel())
        }
      }
  }
}
