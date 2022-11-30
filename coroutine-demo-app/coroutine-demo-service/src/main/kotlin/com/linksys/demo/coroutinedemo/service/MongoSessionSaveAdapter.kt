package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.converter.toModel
import com.linksys.demo.coroutinedemo.model.CreateTokenModel
import com.linksys.demo.coroutinedemo.model.TokenModel
import com.linksys.demo.coroutinedemo.mongo.entity.MongoRefreshEntity
import com.linksys.demo.coroutinedemo.mongo.entity.MongoSessionEntity
import com.linksys.demo.coroutinedemo.mongo.repository.MongoRefreshRepository
import com.linksys.demo.coroutinedemo.mongo.repository.MongoSessionRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty("app.persistence", havingValue = "mongo")
class MongoSessionSaveAdapter(
  private val sessionRepository: MongoSessionRepository,
  private val refreshRepository: MongoRefreshRepository,
) : SessionSaveAdapter {

  override suspend fun saveSession(m: CreateTokenModel): TokenModel {
    val t = m.token
    val entity2 = MongoSessionEntity(m.site.id, t.token, m.identity, t.expiryTime, m.originatedFrom)
    return sessionRepository.save(entity2)
      .awaitSingle()
      .toModel()
  }

  override suspend fun saveRefresh(m: CreateTokenModel): TokenModel {
    val t = m.token
    val entity = MongoRefreshEntity(m.site.id, t.token, m.identity, t.expiryTime)
    return refreshRepository.save(entity)
      .awaitSingle()
      .toModel()
  }
}
