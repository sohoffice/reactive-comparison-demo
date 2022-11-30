package com.linksys.demo.imperativedemo.entity

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "refresh_token")
data class PostgresRefreshEntity(
  @Column(name = "external_id")
  val externalId: UUID,
  @Column(name = "identity_id")
  val identityId: UUID,
  @Column(name = "site_id")
  val siteId: UUID,
  val token: String,
  @Column(name = "expiry_time")
  val expiryTime: Instant? = null,
  @Column(name = "parent_id")
  val parentId: Long? = null,
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null
)
