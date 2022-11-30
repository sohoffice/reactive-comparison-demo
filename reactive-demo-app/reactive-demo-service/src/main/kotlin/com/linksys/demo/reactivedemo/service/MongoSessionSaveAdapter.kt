package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.converter.toModel
import com.linksys.demo.reactivedemo.model.TokenCreatingModel
import com.linksys.demo.reactivedemo.model.TokenModel
import com.linksys.demo.reactivedemo.mongo.entity.MongoRefreshEntity
import com.linksys.demo.reactivedemo.mongo.entity.MongoSessionEntity
import com.linksys.demo.reactivedemo.mongo.repository.MongoRefreshRepository
import com.linksys.demo.reactivedemo.mongo.repository.MongoSessionRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@ConditionalOnProperty("app.persistence", havingValue = "mongo")
class MongoSessionSaveAdapter(
  private val sessionRepository: MongoSessionRepository,
  private val refreshRepository: MongoRefreshRepository,
) : SessionSaveAdapter {

  override fun saveSession(m: TokenCreatingModel): Mono<TokenModel> {
    val t = m.token
    val entity2 = MongoSessionEntity(m.site.id, t.token, m.identity, t.expiryTime, m.originatedFrom)
    return sessionRepository.save(entity2)
      .map(MongoSessionEntity::toModel)
  }

  override fun saveRefresh(m: TokenCreatingModel): Mono<TokenModel> {
    val t = m.token
    val entity = MongoRefreshEntity(m.site.id, t.token, m.identity, t.expiryTime)
    return refreshRepository.save(entity)
      .map(MongoRefreshEntity::toModel)
  }
}
