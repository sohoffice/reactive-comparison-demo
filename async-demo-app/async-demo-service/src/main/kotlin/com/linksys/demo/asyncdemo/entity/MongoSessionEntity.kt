package com.linksys.demo.asyncdemo.mongo.entity

import com.linksys.demo.asyncdemo.model.IdentityModel
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document("session")
data class MongoSessionEntity(
  @Id
  val id: ObjectId = ObjectId.get(),
  val externalId: UUID = UUID.randomUUID(),
  val siteId: UUID,
  val token: String,
  val identity: UUID,
  val expiryTime: Instant? = null,
  val originatedFrom: UUID? = null,
) {
  constructor(siteId: UUID, token: String, identity: IdentityModel, expiryTime: Instant?, refreshedFrom: UUID?) : this(
    ObjectId.get(),
    UUID.randomUUID(),
    siteId, token, identity.id, expiryTime, refreshedFrom
  )
}
