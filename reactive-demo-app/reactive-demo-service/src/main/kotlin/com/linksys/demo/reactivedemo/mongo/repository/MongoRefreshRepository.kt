package com.linksys.demo.reactivedemo.mongo.repository

import com.linksys.demo.reactivedemo.mongo.entity.MongoRefreshEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoRefreshRepository : ReactiveMongoRepository<MongoRefreshEntity, ObjectId> {
}
