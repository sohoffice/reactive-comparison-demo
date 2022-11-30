package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.converter.toModel
import com.linksys.demo.reactivedemo.model.TokenModel
import com.linksys.demo.reactivedemo.mongo.entity.MongoSessionEntity
import com.linksys.demo.reactivedemo.mongo.repository.MongoSessionRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
@ConditionalOnProperty("app.persistence", havingValue = "mongo")
class MongoSessionGetAdapter(
  private val sessionRepository: MongoSessionRepository
) : SessionGetAdapter {

  @Transactional
  override fun get(token: String): Mono<TokenModel> {
    return sessionRepository.findByToken(token)
      .map(MongoSessionEntity::toModel)
  }
}
