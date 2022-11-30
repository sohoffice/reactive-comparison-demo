package com.linksys.demo.coroutinedemo.converter

import com.linksys.demo.coroutinedemo.model.Site
import com.linksys.demo.coroutinedemo.model.TokenModel
import com.linksys.demo.coroutinedemo.mongo.entity.MongoRefreshEntity
import com.linksys.demo.coroutinedemo.mongo.entity.MongoSessionEntity

fun MongoSessionEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, this.identity, this.expiryTime)
}

fun MongoRefreshEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, this.identity, this.expiryTime)
}
