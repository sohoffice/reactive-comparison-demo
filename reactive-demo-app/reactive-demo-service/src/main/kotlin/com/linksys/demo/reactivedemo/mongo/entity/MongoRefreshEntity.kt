package com.linksys.demo.reactivedemo.mongo.entity

import com.linksys.demo.reactivedemo.model.IdentityModel
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document("refresh")
data class MongoRefreshEntity(
  @Id
  val id: ObjectId = ObjectId.get(),
  val externalId: UUID = UUID.randomUUID(),
  val siteId: UUID,
  val identity: UUID,
  val token: String,
  val expiryTime: Instant? = null,
) {
  constructor(siteId: UUID, token: String, identity: IdentityModel, expiryTime: Instant? = null) : this(
    ObjectId.get(),
    UUID.randomUUID(),
    siteId,
    identity.id,
    token,
    expiryTime
  )

}
