package com.linksys.demo.coroutinedemo.mongo.entity

import com.linksys.demo.coroutinedemo.model.IdentityModel
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
  val token: String,
  val identity: UUID,
  val expiryTime: Instant? = null,
) {
  constructor(siteId: UUID, token: String, identity: IdentityModel, expiryTime: Instant? = null) : this(
    ObjectId.get(),
    UUID.randomUUID(),
    siteId,
    token,
    identity.id,
    expiryTime
  )

}
