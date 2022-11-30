package com.linksys.demo.imperativedemo.entity

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "session_token")
data class PostgresSessionEntity(
  @Column(name = "external_id")
  val externalId: UUID,
  @Column(name = "site_id")
  val siteId: UUID,
  @Column(name = "identity_id")
  val identityId: UUID,
  val token: String,
  @Column(name = "expiry_time")
  val expiryTime: Instant? = null,
  @Column(name = "created_by_refresh_token_id")
  val createdByRefreshTokenId: Long? = null,
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null
)
