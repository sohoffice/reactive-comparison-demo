package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.converter.toModel
import com.linksys.demo.coroutinedemo.model.TokenModel
import com.linksys.demo.coroutinedemo.mongo.repository.MongoSessionRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@ConditionalOnProperty("app.persistence", havingValue = "mongo")
class MongoSessionGetAdapter(
  private val sessionRepository: MongoSessionRepository
) : SessionGetAdapter {

  @Transactional
  override suspend fun get(token: String): TokenModel? {
    val it = sessionRepository.findByToken(token)
      .awaitSingleOrNull()
    return it?.toModel()
  }
}
