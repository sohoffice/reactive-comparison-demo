package com.linksys.demo.asyncdemo.repository

import com.linksys.demo.asyncdemo.mongo.entity.MongoSessionEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import java.util.concurrent.CompletableFuture

@Repository
interface MongoSessionRepository : ReactiveMongoRepository<MongoSessionEntity, ObjectId> {

  @Async
  fun findByToken(token: String): CompletableFuture<MongoSessionEntity?>

}
