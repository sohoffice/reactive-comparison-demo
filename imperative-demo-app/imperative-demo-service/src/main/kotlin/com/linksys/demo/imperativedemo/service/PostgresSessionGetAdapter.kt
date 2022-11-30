package com.linksys.demo.imperativedemo.service

import com.linksys.demo.imperativedemo.converter.toModel
import com.linksys.demo.imperativedemo.model.TokenModel
import com.linksys.demo.imperativedemo.repository.PostgresSessionRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty("app.persistence", havingValue = "postgres")
class PostgresSessionGetAdapter(
  private val sessionRepository: PostgresSessionRepository
) : SessionGetAdapter {
  override fun get(token: String): TokenModel? {
    val it = sessionRepository.findByToken(token)
    return it?.toModel()
  }
}
