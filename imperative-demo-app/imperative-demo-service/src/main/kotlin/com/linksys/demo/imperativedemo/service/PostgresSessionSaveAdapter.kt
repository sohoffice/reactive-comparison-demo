package com.linksys.demo.imperativedemo.service

import com.linksys.demo.imperativedemo.converter.toModel
import com.linksys.demo.imperativedemo.entity.PostgresRefreshEntity
import com.linksys.demo.imperativedemo.entity.PostgresSessionEntity
import com.linksys.demo.imperativedemo.model.SessionCreatingModel
import com.linksys.demo.imperativedemo.model.SessionModel
import com.linksys.demo.imperativedemo.repository.PostgresRefreshRepository
import com.linksys.demo.imperativedemo.repository.PostgresSessionRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import java.util.*

@Service
@ConditionalOnProperty("app.persistence", havingValue = "postgres")
class PostgresSessionSaveAdapter(
  private val sessionRepository: PostgresSessionRepository,
  private val refreshRepository: PostgresRefreshRepository
) : SessionSaveAdapter {
  override fun save(m: SessionCreatingModel): SessionModel {
    val st = PostgresSessionEntity(UUID.randomUUID(), m.identity.id, m.site.id, m.sessionToken.token, m.sessionToken.expiryTime)
    val sess = sessionRepository.save(st)
    val refresh = if (m.refreshToken != null) {
      // do something
      val rt = PostgresRefreshEntity(UUID.randomUUID(), m.identity.id, m.site.id, m.refreshToken.token, m.refreshToken.expiryTime)
      refreshRepository.save(rt)
    } else null
    return SessionModel(m.site, sess.toModel(), refresh?.toModel())
  }
}
