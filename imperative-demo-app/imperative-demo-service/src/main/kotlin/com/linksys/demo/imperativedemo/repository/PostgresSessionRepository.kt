package com.linksys.demo.imperativedemo.repository

import com.linksys.demo.imperativedemo.entity.PostgresSessionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostgresSessionRepository : JpaRepository<PostgresSessionEntity, Long> {

  fun findByToken(token: String): PostgresSessionEntity?
}
