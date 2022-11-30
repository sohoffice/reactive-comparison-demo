package com.linksys.demo.coroutinedemo.mongo.repository

import com.linksys.demo.coroutinedemo.mongo.entity.MongoSessionEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface MongoSessionRepository : ReactiveMongoRepository<MongoSessionEntity, ObjectId> {

  fun findByToken(token: String): Mono<MongoSessionEntity>

}
