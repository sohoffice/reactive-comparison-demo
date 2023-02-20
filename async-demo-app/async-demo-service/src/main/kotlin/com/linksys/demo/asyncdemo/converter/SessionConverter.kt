package com.linksys.demo.asyncdemo.converter

import com.linksys.demo.asyncdemo.model.Site
import com.linksys.demo.asyncdemo.model.TokenModel
import com.linksys.demo.asyncdemo.mongo.entity.MongoRefreshEntity
import com.linksys.demo.asyncdemo.mongo.entity.MongoSessionEntity

fun MongoSessionEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, this.identity, this.expiryTime)
}

fun MongoRefreshEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, this.identity, this.expiryTime)
}
