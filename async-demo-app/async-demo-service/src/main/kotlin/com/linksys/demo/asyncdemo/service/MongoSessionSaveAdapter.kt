package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.converter.toModel
import com.linksys.demo.asyncdemo.model.CreateTokenModel
import com.linksys.demo.asyncdemo.model.TokenModel
import com.linksys.demo.asyncdemo.mongo.entity.MongoRefreshEntity
import com.linksys.demo.asyncdemo.mongo.entity.MongoSessionEntity
import com.linksys.demo.asyncdemo.repository.MongoRefreshRepository
import com.linksys.demo.asyncdemo.repository.MongoSessionRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
@ConditionalOnProperty("app.persistence", havingValue = "mongo")
class MongoSessionSaveAdapter(
  private val sessionRepository: MongoSessionRepository,
  private val refreshRepository: MongoRefreshRepository,
) : SessionSaveAdapter {

  @Async
  override fun saveSession(m: CreateTokenModel): CompletableFuture<TokenModel> {
    val t = m.token
    val entity2 = MongoSessionEntity(m.site.id, t.token, m.identity, t.expiryTime, m.originatedFrom)
    return sessionRepository.save(entity2)
      .toFuture()
      .thenApply {
        it.toModel()
      }
  }

  @Async
  override fun saveRefresh(m: CreateTokenModel): CompletableFuture<TokenModel> {
    val t = m.token
    val entity = MongoRefreshEntity(m.site.id, t.token, m.identity, t.expiryTime)
    return refreshRepository.save(entity)
      .toFuture()
      .thenApply {
        it.toModel()
      }
  }
}
