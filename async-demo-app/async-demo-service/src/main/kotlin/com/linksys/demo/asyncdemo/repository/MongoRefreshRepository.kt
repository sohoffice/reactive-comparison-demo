package com.linksys.demo.asyncdemo.repository

import com.linksys.demo.asyncdemo.mongo.entity.MongoRefreshEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoRefreshRepository : ReactiveMongoRepository<MongoRefreshEntity, ObjectId> {
}
